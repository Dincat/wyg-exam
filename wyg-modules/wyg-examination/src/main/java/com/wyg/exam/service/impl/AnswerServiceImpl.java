package com.wyg.exam.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.wyg.common.core.utils.JsonUtil;
import com.wyg.exam.constants.AnswerConstant;
import com.wyg.exam.constants.MqConstant;
import com.wyg.exam.domain.Examination;
import com.wyg.exam.domain.ExaminationRecord;
import com.wyg.exam.domain.ExaminationSubject;
import com.wyg.exam.domain.vo.AnswerVO;
import com.wyg.exam.domain.vo.RankInfoVO;
import com.wyg.exam.domain.vo.StartExamVO;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.enums.SubjectTypeEnum;
import com.wyg.exam.enums.SubmitStatusEnum;
import com.wyg.exam.handler.AnswerHandleResult;
import com.wyg.exam.handler.impl.ChoicesAnswerHandler;
import com.wyg.exam.handler.impl.JudgementAnswerHandler;
import com.wyg.exam.handler.impl.MultipleChoicesAnswerHandler;
import com.wyg.exam.handler.impl.ShortAnswerHandler;
import com.wyg.exam.service.*;
import com.wyg.exam.utils.AnswerHandlerUtils;
import com.wyg.exam.utils.ExamRecordUtils;
import com.github.pagehelper.PageInfo;
import com.wyg.common.core.constant.HttpStatus;
import com.wyg.common.core.constant.UserConstants;
import com.wyg.common.core.domain.R;
import com.wyg.common.core.exception.ServiceException;
import com.wyg.common.redis.service.RedisService;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.system.api.RemoteUserService;
import com.wyg.system.api.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import com.wyg.exam.mapper.AnswerMapper;
import com.wyg.exam.domain.Answer;
import org.springframework.transaction.annotation.Transactional;

/**
 * 答题Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Slf4j
@Service
public class AnswerServiceImpl implements IAnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    IExaminationRecordService examRecordService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    IExaminationSubjectService examinationSubjectService;

    @Autowired
    ChoicesAnswerHandler choicesHandler;

    @Autowired
    JudgementAnswerHandler judgementHandler;

    @Autowired
    ShortAnswerHandler shortAnswerHandler;

    @Autowired
    MultipleChoicesAnswerHandler multipleChoicesHandler;

    @Autowired
    IExaminationService examinationService;

    @Autowired
    RedisService redisService;

    @Autowired
    IExamExaminationRankService examinationRankService;

    /**
     * 查询答题
     *
     * @param id 答题主键
     * @return 答题
     */
    @Override
    public Answer selectAnswerById(Long id) {
        return answerMapper.selectAnswerById(id);
    }

    /**
     * 查询答题列表
     *
     * @param answer 答题
     * @return 答题
     */
    @Override
    public List<Answer> selectAnswerList(Answer answer) {
        return answerMapper.selectAnswerList(answer);
    }

    /**
     * 新增答题
     *
     * @param answer 答题
     * @return 结果
     */
    @Override
    public int insertAnswer(Answer answer) {
        answer.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(), SecurityUtils.getTenantCode());
        return answerMapper.insertAnswer(answer);
    }

    /**
     * 修改答题
     *
     * @param answer 答题
     * @return 结果
     */
    @Override
    public int updateAnswer(Answer answer) {
        answer.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(), SecurityUtils.getTenantCode());
        return answerMapper.updateAnswer(answer);
    }

    /**
     * 批量删除答题
     *
     * @param ids 需要删除的答题主键
     * @return 结果
     */
    @Override
    public int deleteAnswerByIds(Long[] ids) {
        return answerMapper.deleteAnswerByIds(ids);
    }

    /**
     * 删除答题信息
     *
     * @param id 答题主键
     * @return 结果
     */
    @Override
    public int deleteAnswerById(Long id) {
        return answerMapper.deleteAnswerById(id);
    }

    /**
     * 答题详情
     *
     * @param recordId         recordId
     * @param currentSubjectId currentSubjectId
     * @param nextSubjectType  nextSubjectType
     * @param nextType         nextType
     * @return AnswerDto
     * @author tangyi
     * @date 2019/06/18 23:05
     */
    public AnswerVO answerInfo(Long recordId, Long currentSubjectId, Integer nextSubjectType, Integer nextType) {
        ExaminationRecord record = examRecordService.selectExaminationRecordById(recordId);
        SubjectVO subjectDto;
        // 题目为空，则加载第一题
        if (currentSubjectId == null) {
            subjectDto = subjectService.findFirstSubjectByExaminationId(record.getExaminationId());
        } else {
            ExaminationSubject examinationSubject = new ExaminationSubject();
            examinationSubject.setExaminationId(record.getExaminationId());
            examinationSubject.setSubjectId(currentSubjectId);

            // 查询该考试和指定序号的题目的关联信息
            // 下一题
            if (AnswerConstant.NEXT.equals(nextType)) {
                examinationSubject = examinationSubjectService.getByPreviousId(examinationSubject);
            } else if (AnswerConstant.PREVIOUS.equals(nextType)) {
                // 上一题
                examinationSubject = examinationSubjectService.getPreviousByCurrentId(examinationSubject);
            } else {
                examinationSubject = examinationSubjectService.findByExaminationIdAndSubjectId(examinationSubject);
            }
            if (examinationSubject == null)
                throw new ServiceException("ID为" + currentSubjectId + "的题目不存在");
            // 查询题目的详细信息
            subjectDto = subjectService.getSubject(examinationSubject.getSubjectId(), examinationSubject.getType());
        }
        AnswerVO answerDto = new AnswerVO();
        answerDto.setSubject(subjectDto);
        // 查询答题
        Answer answer = new Answer();
        answer.setSubjectId(subjectDto.getId());
        answer.setExamRecordId(recordId);
        Answer userAnswer = this.selectAnswerList(answer).stream().findFirst().orElse(null);
        if (userAnswer == null)
            userAnswer = answer;
        BeanUtils.copyProperties(userAnswer, answerDto);
        answerDto.setDuration(ExamRecordUtils.getExamDuration(userAnswer.getStartTime(), userAnswer.getEndTime()));
        // 判断正误
        SubjectTypeEnum subjectType = SubjectTypeEnum.matchByValue(subjectDto.getType());
        if (subjectType != null) {
            switch (subjectType) {
                case CHOICES:
                    choicesHandler.judgeOptionRight(userAnswer, subjectDto);
                    break;
                case MULTIPLE_CHOICES:
                    multipleChoicesHandler.judgeOptionRight(userAnswer, subjectDto);
                    break;
                case SHORT_ANSWER:
                    shortAnswerHandler.judgeRight(userAnswer, subjectDto);
                    break;
                case JUDGEMENT:
                    judgementHandler.judgeRight(userAnswer, subjectDto);
                    break;
                default:
                    break;
            }
        }
//        R<List<SysUser>> userVoResponseBean = remoteUserService.findListByIds(new Long[]{record.getUserId()});
//
//        if(userVoResponseBean.getCode()== HttpStatus.SUCCESS)
//
//        if (userVoResponseBean.getCode()== HttpStatus.SUCCESS && CollectionUtils.isNotEmpty(userVoResponseBean.getData())) {
//            SysUser userVo = userVoResponseBean.getData().get(0);
//            answerDto.setUserName(userVo.getUserName());
//        }
        return answerDto;
    }

    /**
     * 更新答题总分
     *
     * @param answer answer
     * @return int
     * @author tangyi
     * @date 2019/1/3 14:27
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "answer", key = "#answer.id")
    public int updateScore(Answer answer) {
        answer.setAnswer(AnswerHandlerUtils.replaceComma(answer.getAnswer()));
        // 加分减分逻辑
        Answer oldAnswer = this.selectAnswerById(answer.getId());

        if (oldAnswer == null) return 0;

        if (!oldAnswer.getAnswerType().equals(answer.getAnswerType())) {
            ExaminationRecord record = new ExaminationRecord();
            record.setId(oldAnswer.getExamRecordId());
            record = examRecordService.selectExaminationRecordById(oldAnswer.getExamRecordId());
            if (record == null) {
                throw new ServiceException("ExamRecord is null");
            }
            if (record.getCorrectNumber() == null) record.setCorrectNumber(0);
            if (record.getIncorrectNumber() == null) record.setIncorrectNumber(0);
            if (record.getScore() == null) record.setScore(new Double(0.0));

            Double oldScore = record.getScore();
            if (AnswerConstant.RIGHT.equals(answer.getAnswerType())) {
                // 加分
                record.setCorrectNumber(record.getCorrectNumber() + 1);
                record.setIncorrectNumber(record.getIncorrectNumber() - 1);
                record.setScore(record.getScore() + answer.getScore());
            } else if (AnswerConstant.WRONG.equals(answer.getAnswerType())) {
                // 减分
                record.setCorrectNumber(record.getCorrectNumber() - 1);
                record.setIncorrectNumber(record.getIncorrectNumber() + 1);
                record.setScore(record.getScore() - answer.getScore());
            }
            if (examRecordService.updateExaminationRecord(record) > 0) {
                log.info("Update answer success, examRecordId: {}, oldScore: {}, newScore: {}", oldAnswer.getExamRecordId(), oldScore, record.getScore());
            }
        }
        return this.updateAnswer(answer);
    }

    /**
     * 完成批改
     *
     * @param examRecord examRecord
     * @return Boolean
     * @author tangyi
     * @date 2019/06/19 14:44
     */
    public Boolean completeMarking(ExaminationRecord examRecord) {
        long start = System.currentTimeMillis();
        examRecord = examRecordService.selectExaminationRecordById(examRecord.getId());
        if (examRecord == null)
            throw new ServiceException("考试记录不存在.");
        Answer answer = new Answer();
        answer.setExamRecordId(examRecord.getId());
        List<Answer> answers = this.selectAnswerList(answer);
        if (CollectionUtils.isNotEmpty(answers)) {
            long correctNumber = answers.stream()
                    .filter(tempAnswer -> tempAnswer.getAnswerType().equals(AnswerConstant.RIGHT)).count();
            // 总分
            Double score = answers.stream().mapToDouble(Answer::getScore).sum();
            examRecord.setScore(score);
            examRecord.setSubmitStatus(SubmitStatusEnum.CALCULATED.getValue());
            examRecord.setCorrectNumber((int) correctNumber);
            examRecord.setIncorrectNumber(answers.size() - examRecord.getCorrectNumber());
            examRecordService.updateExaminationRecord(examRecord);
            log.debug("Submit done, username: {}, examinationId: {}, score: {}, time consuming: {}ms", examRecord.getCreator(), examRecord.getExaminationId(),
                    score, System.currentTimeMillis() - start);
        }
        return Boolean.TRUE;
    }


    @Override
    public SubjectVO subjectAnswer(AnswerVO answerVO) {
        // 查找考试记录
        ExaminationRecord examRecord = examRecordService.selectExaminationRecordById(answerVO.getExamRecordId());
        if (examRecord == null)
            throw new ServiceException("考试记录不存在.");

        // 考试ID，题目ID查找关联关系
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setExaminationId(examRecord.getExaminationId());
        examinationSubject.setSubjectId(answerVO.getSubjectId());

        List<ExaminationSubject> examinationSubjectList = examinationSubjectService.selectExaminationSubjectList(examinationSubject);
        if (CollectionUtils.isEmpty(examinationSubjectList))
            throw new ServiceException("序号为" + answerVO.getSubjectId() + "的题目不存在.");

        // 查询下一题
        SubjectVO subject;
        if (answerVO.getNextSubjectId() != null) {
            subject = subjectService.getSubject(answerVO.getNextSubjectId(), answerVO.getNextType());
        } else {
            subject = subjectService.getNextByCurrentIdAndType(examRecord.getExaminationId(), answerVO.getSubjectId(), examinationSubjectList.get(0).getType(), answerVO.getNextType());
        }
        if (subject == null) {
            log.error("Subject does not exist: {}", answerVO.getSubjectId());
            return null;
        }

        // 查找答题
        Answer answer = new Answer();
        answer.setSubjectId(subject.getId());
        answer.setExamRecordId(answerVO.getExamRecordId());
        Answer userAnswer = this.selectAnswerList(answer).stream().findFirst().orElse(null);
        userAnswer = userAnswer == null ? new Answer() : userAnswer;
        // 设置答题
        subject.setAnswer(userAnswer);
        subject.setExaminationRecordId(answerVO.getExamRecordId());
        return subject;
    }

    /**
     * 保存答题
     *
     * @param answerDto  answerDto
     * @param userCode   userCode
     * @param sysCode    sysCode
     * @param tenantCode tenantCode
     * @return int
     * @author tangyi
     * @date 2019/05/01 11:42
     */
    @Transactional
    public int save(AnswerVO answerDto, String userCode, String sysCode, String tenantCode) {
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerDto, answer);
        Answer tempAnswer = this.selectAnswerList(answer).stream().findFirst().orElse(null);
        if (tempAnswer != null) {
            tempAnswer.setCommonValue(userCode, sysCode, tenantCode);
            tempAnswer.setAnswer(answer.getAnswer());
            tempAnswer.setType(answer.getType());
            tempAnswer.setEndTime(tempAnswer.getModifyDate());
            return this.updateAnswer(tempAnswer);
        } else {
            answer.setCommonValue(userCode, sysCode, tenantCode);
            answer.setMarkStatus(AnswerConstant.TO_BE_MARKED);
            answer.setAnswerType(AnswerConstant.WRONG);
            answer.setEndTime(answer.getModifyDate());
            return this.insertAnswer(answer);
        }
    }


    @Override
    public SubjectVO saveAndNext(AnswerVO answerVO, Integer type, Long nextSubjectId, Integer nextSubjectType) {
        String userCode = SecurityUtils.getUsername();
        String sysCode = SecurityUtils.getApplicationCode();
        String tenantCode = SecurityUtils.getTenantCode();
        if (this.save(answerVO, userCode, sysCode, tenantCode) > 0) {
            // 查询下一题
            SubjectVO subjectVO = this.subjectAnswer(answerVO.getSubjectId(), answerVO.getExamRecordId(),
                    type, nextSubjectId, nextSubjectType);
            return subjectVO;
        }
        return null;
    }

    /**
     * 查询题目和答题
     *
     * @param subjectId       subjectId
     * @param examRecordId    examRecordId
     * @param nextType        -1：当前题目，0：下一题，1：上一题
     * @param nextSubjectId   nextSubjectId
     * @param nextSubjectType 下一题的类型，选择题、判断题
     * @return SubjectDto
     * @author tangyi
     * @date 2019/04/30 17:10
     */
    @Transactional
    public SubjectVO subjectAnswer(Long subjectId, Long examRecordId, Integer nextType, Long nextSubjectId, Integer nextSubjectType) {
        // 查找考试记录
        ExaminationRecord examRecord = examRecordService.selectExaminationRecordById(examRecordId);
        if (examRecord == null)
            throw new ServiceException("考试记录不存在.");

        // 考试ID，题目ID查找关联关系
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setExaminationId(examRecord.getExaminationId());
        examinationSubject.setSubjectId(subjectId);

        List<ExaminationSubject> examinationSubjectList = examinationSubjectService.selectExaminationSubjectList(examinationSubject);

        if (CollectionUtils.isEmpty(examinationSubjectList))
            throw new ServiceException("序号为" + subjectId + "的题目不存在.");

        // 查询下一题
        SubjectVO subject;
        if (nextSubjectId != null) {
            subject = subjectService.getSubject(nextSubjectId, nextSubjectType);
        } else {
            subject = subjectService.getNextByCurrentIdAndType(examRecord.getExaminationId(), subjectId, examinationSubjectList.get(0).getType(), nextType);
        }
        if (subject == null) {
            log.error("Subject does not exist: {}", subjectId);
            return null;
        }

        // 查找答题
        Answer answer = new Answer();
        answer.setSubjectId(subject.getId());
        answer.setExamRecordId(examRecordId);
        Answer userAnswer = this.selectAnswerList(answer).stream().findFirst().orElse(null);
        userAnswer = userAnswer == null ? new Answer() : userAnswer;
        // 设置答题
        subject.setAnswer(userAnswer);
        subject.setExaminationRecordId(examRecordId);
        return subject;
    }

    /**
     * 开始考试
     *
     * @param examRecord examRecord
     * @return StartExamDto
     * @author tangyi
     * @date 2019/04/30 23:06
     */
    @Transactional
    @Override
    public StartExamVO start(ExaminationRecord examRecord) {
        // 创建考试记录
        if (examRecord.getExaminationId() == null)
            throw new ServiceException("参数校验失败，考试id为空！");

        StartExamVO startExamDto = new StartExamVO();
        SysUser sysUser = SecurityUtils.getLoginUser().getSysUser();

        // 查找考试信息
        Examination examination = examinationService.selectExaminationById(examRecord.getExaminationId());

        boolean isNewRecord = false;
        if (examRecord.getId() != null && examRecord.getId().longValue() > 0) {
            examRecord = examRecordService.selectExaminationRecordById(examRecord.getId());
        } else {

            if (!examination.getCanRepeat().equals(UserConstants.YES)) { //不能和重复答题
                ExaminationRecord query = new ExaminationRecord();
                query.setExaminationId(examRecord.getExaminationId());
                query.setUserId(SecurityUtils.getUserId());
                List<ExaminationRecord> historyRecord = examRecordService.selectExaminationRecordList(query);
                if (historyRecord != null && historyRecord.size() > 0)
                    throw new ServiceException("您已有该试卷的作答记录，该试卷不允许重复作答。");
            }


            examRecord.setCommonValue(sysUser.getUserName(), SecurityUtils.getApplicationCode(), SecurityUtils.getTenantCode());
            examRecord.setUserId(sysUser.getUserId());
            examRecord.setStartTime(new Date());
            examRecord.setUserName(sysUser.getNickName());

            String deptName=sysUser.getDept()==null?"":sysUser.getDept().getDeptName();

            examRecord.setDeptName(deptName);
            examRecord.setExaminationName(examination.getExaminationName());
            examRecord.setExamType(examination.getType());
            // 默认未提交状态
            examRecord.setSubmitStatus(SubmitStatusEnum.UNSUBMITTED.getValue());
            isNewRecord = true;

            // 保存考试记录
            if (examRecordService.insertExaminationRecord(examRecord) < 1) {
                throw new ServiceException("创建考试记录失败。");
            }
        }


        startExamDto.setExamination(examination);
        startExamDto.setExamRecord(examRecord);


        Answer answer = null;
        SubjectVO subjectDto = null;

        if (!isNewRecord) {
            answer = this.selectLastByRecordId(examRecord.getId());
        }

        if (answer == null) {
            // 根据题目ID，类型获取第一题的详细信息
            subjectDto = subjectService.findFirstSubjectByExaminationId(examRecord.getExaminationId());

            // 创建第一题的答题
            answer = new Answer();
            answer.setCommonValue(sysUser.getUserName(), SecurityUtils.getApplicationCode(), SecurityUtils.getTenantCode());
            answer.setExamRecordId(examRecord.getId());
            answer.setSubjectId(subjectDto.getId());
            answer.setType(subjectDto.getType());
            // 默认待批改状态
            answer.setMarkStatus(AnswerConstant.TO_BE_MARKED);
            answer.setAnswerType(AnswerConstant.WRONG);
            answer.setStartTime(answer.getCreateDate());
            // 保存答题
            this.insertAnswer(answer);
        } else {
            subjectDto = subjectService.getSubject(answer.getSubjectId(), answer.getType());
        }

        subjectDto.setAnswer(answer);
        startExamDto.setSubjectVO(subjectDto);
        return startExamDto;
    }

    /**
     * 分类答题
     *
     * @param answers answers
     * @return Map
     * @author tangyi
     * @date 2019/06/18 16:32
     */
    private Map<String, List<Answer>> distinctAnswer(List<Answer> answers) {
        Map<String, List<Answer>> distinctMap = new HashMap<>();
        answers.stream().collect(Collectors.groupingBy(Answer::getType, Collectors.toList())).forEach((type, temp) -> {
            // 匹配类型
            SubjectTypeEnum subjectType = SubjectTypeEnum.matchByValue(type);
            if (subjectType != null) {
                switch (subjectType) {
                    case CHOICES:
                        distinctMap.put(SubjectTypeEnum.CHOICES.name(), temp);
                        break;
                    case MULTIPLE_CHOICES:
                        distinctMap.put(SubjectTypeEnum.MULTIPLE_CHOICES.name(), temp);
                        break;
                    case SHORT_ANSWER:
                        distinctMap.put(SubjectTypeEnum.SHORT_ANSWER.name(), temp);
                        break;
                    case JUDGEMENT:
                        distinctMap.put(SubjectTypeEnum.JUDGEMENT.name(), temp);
                        break;
                    default:
                        break;
                }
            }
        });
        return distinctMap;
    }

    /**
     * 自动判分
     *
     * @param distinctAnswer distinctAnswer
     * @return ResponseBean
     * @author tangyi
     * @date 2020/03/15 16:21
     */
    public AnswerHandleResult handleAll(Map<String, List<Answer>> distinctAnswer) {
        // 暂时只自动统计单选题、多选题、判断题，简答题由老师阅卷批改
        AnswerHandleResult choiceResult = choicesHandler.handle(distinctAnswer.get(SubjectTypeEnum.CHOICES.name()));
        AnswerHandleResult multipleResult = multipleChoicesHandler.handle(distinctAnswer.get(SubjectTypeEnum.MULTIPLE_CHOICES.name()));
        AnswerHandleResult judgementResult = judgementHandler.handle(distinctAnswer.get(SubjectTypeEnum.JUDGEMENT.name()));
        AnswerHandleResult shortAnswerResult = shortAnswerHandler.handle(distinctAnswer.get(SubjectTypeEnum.SHORT_ANSWER.name()));
        return AnswerHandlerUtils.addAll(Arrays.asList(choiceResult, multipleResult, judgementResult, shortAnswerResult));
    }

    /**
     * 提交答卷，自动统计选择题得分
     *
     * @param answer answer
     * @author tangyi
     * @date 2018/12/26 14:09
     */
    @Transactional
    public void submit(Answer answer) {
        long start = System.currentTimeMillis();
        String currentUsername = answer.getModifier();
        // 查找已提交的题目
        List<Answer> answerList = selectAnswerList(answer);
        if (CollectionUtils.isEmpty(answerList))
            return;
        // 成绩
        ExaminationRecord record = new ExaminationRecord();
        // 分类题目
        Map<String, List<Answer>> distinctAnswer = this.distinctAnswer(answerList);
        AnswerHandleResult result = handleAll(distinctAnswer);
        // 记录总分、正确题目数、错误题目数
        record.setScore(result.getScore());
        record.setCorrectNumber(result.getCorrectNum());
        record.setIncorrectNumber(result.getInCorrectNum());
        // 更新答题状态
        distinctAnswer.values().forEach(answers -> answers.forEach(this::updateAnswer));

        if ((record.getIncorrectNumber().intValue() + record.getCorrectNumber().intValue()) >= answerList.size()) {
            // 更新状态为统计完成，否则需要阅卷完成后才更改统计状态
            record.setSubmitStatus(SubmitStatusEnum.CALCULATED.getValue());
        }
        // 保存成绩
        record.setCommonValue(currentUsername, answer.getApplicationCode(), answer.getTenantCode());
        record.setId(answer.getExamRecordId());
        record.setEndTime(record.getCreateDate());

        examRecordService.updateExaminationRecord(record);

        record=examRecordService.selectExaminationRecordById(record.getId());
        // 更新排名数据
        examinationRankService.updateRank(record);
        log.debug("Submit examination, username: {}，time consuming: {}ms", currentUsername, System.currentTimeMillis() - start);
    }




    /**
     * 通过mq异步处理
     * 1. 先发送消息
     * 2. 发送消息成功，更新提交状态，发送失败，返回提交失败
     * 3. 消费消息，计算成绩
     *
     * @param answer answer
     * @return boolean
     * @author tangyi
     * @date 2019/05/03 14:35
     */
    @Transactional
    public boolean submitAsync(Answer answer) {
        long start = System.currentTimeMillis();
        String currentUsername = SecurityUtils.getUsername();
        String applicationCode = SecurityUtils.getApplicationCode();
        String tenantCode = SecurityUtils.getTenantCode();

        answer.setModifier(currentUsername);
        answer.setApplicationCode(applicationCode);
        answer.setTenantCode(tenantCode);

        ExaminationRecord examRecord = new ExaminationRecord();
        examRecord.setCommonValue(currentUsername, applicationCode, tenantCode);
        examRecord.setId(answer.getExamRecordId());
        // 提交时间
        examRecord.setEndTime(new Date());
        examRecord.setSubmitStatus(SubmitStatusEnum.SUBMITTED.getValue());
        // 1. 更新考试状态
        boolean success = examRecordService.updateExaminationRecord(examRecord) > 0;
        // 2. 发送消息
        amqpTemplate.convertAndSend(MqConstant.SUBMIT_EXAMINATION_QUEUE, answer);

        log.debug("Submit examination, username: {}，time consuming: {}ms", currentUsername, System.currentTimeMillis() - start);
        return success;
    }


    public List<AnswerVO> toAnswerVO(List<Answer> answerList) {
        List<AnswerVO> answerVOList = new ArrayList<>();

        if (CollectionUtils.isEmpty(answerList)) {
            return answerVOList;
        }

        answerVOList = answerList.stream().map(tempAnswer -> {
            AnswerVO answerDto = new AnswerVO();
            BeanUtils.copyProperties(tempAnswer, answerDto);
            SubjectVO subjectDto = subjectService.getSubject(tempAnswer.getSubjectId(), tempAnswer.getType());
            answerDto.setSubject(subjectDto);
            // 判断正误
            SubjectTypeEnum subjectType = SubjectTypeEnum.matchByValue(subjectDto.getType());
            if (subjectType != null) {
                switch (subjectType) {
                    case CHOICES:
                        choicesHandler.judgeOptionRight(tempAnswer, subjectDto);
                        break;
                    case MULTIPLE_CHOICES:
                        multipleChoicesHandler.judgeOptionRight(tempAnswer, subjectDto);
                        break;
                    case SHORT_ANSWER:
                        shortAnswerHandler.judgeRight(tempAnswer, subjectDto);
                        break;
                    case JUDGEMENT:
                        judgementHandler.judgeRight(tempAnswer, subjectDto);
                        break;
                    default:
                        break;
                }
            }
            return answerDto;
        }).collect(Collectors.toList());

        return answerVOList;
    }

    public List<AnswerVO> toAnswerVO(List<SubjectVO> subjectVOList,List<Answer> answerList) {
        List<AnswerVO> answerVOList = new ArrayList<>();

        if (CollectionUtils.isEmpty(subjectVOList)) {
            return answerVOList;
        }

        if(answerList==null) answerList=new ArrayList<>();

        List<Answer> finalAnswerList = answerList;

        answerVOList = subjectVOList.stream().map(subjectDto -> {

            Answer tempAnswer= finalAnswerList.stream().filter(a->a.getSubjectId().longValue()==subjectDto.getId()).findFirst().orElse(null);
            if(tempAnswer==null){
                tempAnswer=new Answer();
                tempAnswer.setSubjectId(subjectDto.getId());
                tempAnswer.setType(subjectDto.getType());
                tempAnswer.setAnswerType(1);
                tempAnswer.setScore(new Double(0));
                tempAnswer.setMarkStatus(AnswerConstant.MARKED);
            }

            AnswerVO answerDto = new AnswerVO();
            BeanUtils.copyProperties(tempAnswer, answerDto);
            //SubjectVO subjectDto = subjectService.getSubject(tempAnswer.getSubjectId(), tempAnswer.getType());
            answerDto.setSubject(subjectDto);
            // 判断正误
            SubjectTypeEnum subjectType = SubjectTypeEnum.matchByValue(subjectDto.getType());
            if (subjectType != null) {
                switch (subjectType) {
                    case CHOICES:
                        choicesHandler.judgeOptionRight(tempAnswer, subjectDto);
                        break;
                    case MULTIPLE_CHOICES:
                        multipleChoicesHandler.judgeOptionRight(tempAnswer, subjectDto);
                        break;
                    case SHORT_ANSWER:
                        shortAnswerHandler.judgeRight(tempAnswer, subjectDto);
                        break;
                    case JUDGEMENT:
                        judgementHandler.judgeRight(tempAnswer, subjectDto);
                        break;
                    default:
                        break;
                }
            }
            return answerDto;
        }).collect(Collectors.toList());

        return answerVOList;
    }

    @Override
    public List<RankInfoVO> getRankInfo(Long recordId) {
        List<RankInfoVO> rankInfos = new ArrayList<>();
        // 查询缓存
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisService
                .reverseRangeByScoreWithScores(AnswerConstant.CACHE_PREFIX_RANK + recordId, 0, Integer.MAX_VALUE);

        if (typedTuples != null) {
            return rankInfos;
        }

        // 用户ID列表
        Set<Long> userIds = new HashSet<>();
        typedTuples.forEach(typedTuple -> {
            ExaminationRecord record = JsonUtil.fromJson(typedTuple.getValue(), ExaminationRecord.class);
            if (record != null) {
                RankInfoVO rankInfo = new RankInfoVO();
                rankInfo.setUserId(record.getUserId());
                userIds.add(record.getUserId());
                rankInfo.setScore(typedTuple.getScore());
                rankInfo.setName(record.getUserName());
                //rankInfo.setAvatarUrl();
                rankInfos.add(rankInfo);
            }
        });

        return rankInfos;
    }

    /**
     * 根据考试记录查询最后一道答题
     *
     * @param id 答题主键
     * @return 答题
     */
    public Answer selectLastByRecordId(Long recordId) {
        return answerMapper.selectLastByRecordId(recordId);
    }

}
