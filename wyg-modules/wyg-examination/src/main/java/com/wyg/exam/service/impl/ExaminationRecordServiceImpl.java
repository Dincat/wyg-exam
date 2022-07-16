package com.wyg.exam.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.wyg.exam.domain.*;
import com.wyg.exam.domain.vo.AnswerVO;
import com.wyg.exam.domain.vo.ExaminationDashboardVO;
import com.wyg.exam.domain.vo.ExaminationRecordVO;
import com.wyg.exam.domain.vo.SubjectVO;
import com.wyg.exam.mapper.AnswerMapper;
import com.wyg.exam.service.ICourseService;
import com.wyg.exam.service.IExaminationService;
import com.wyg.exam.service.IExaminationSubjectService;
import com.wyg.exam.utils.ExamRecordUtils;
import com.wyg.common.core.constant.Constants;
import com.wyg.common.core.exception.ServiceException;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.exam.mapper.ExaminationRecordMapper;
import com.wyg.exam.service.IExaminationRecordService;

/**
 * 考试记录Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Slf4j
@Service
public class ExaminationRecordServiceImpl implements IExaminationRecordService {
    @Autowired
    private ExaminationRecordMapper examinationRecordMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    IExaminationService examinationService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    IExaminationSubjectService examinationSubjectService;

    @Autowired
    ICourseService courseService;

    /**
     * 查询考试记录
     *
     * @param id 考试记录主键
     * @return 考试记录
     */
    @Override
    public ExaminationRecord selectExaminationRecordById(Long id) {
        return examinationRecordMapper.selectExaminationRecordById(id);
    }


    /**
     * 查询考试记录列表
     *
     * @param examinationRecord 考试记录
     * @return 考试记录
     */
    @Override
    public List<ExaminationRecord> selectExaminationRecordList(ExaminationRecord examinationRecord) {
        return examinationRecordMapper.selectExaminationRecordList(examinationRecord);
    }


    /**
     * 查询考试记录列表
     *
     * @param examinationRecord 考试记录
     * @return 考试记录
     */
    @Override
    public List<ExaminationRecordVO> selectExaminationRecordVOList(ExaminationRecord examinationRecord) {
        List<ExaminationRecord> examRecordList = examinationRecordMapper.selectExaminationRecordList(examinationRecord);

        List<ExaminationRecordVO> examRecordVOList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(examRecordList)) {
            return examRecordVOList;
        }
        // 查询考试信息
        List<Examination> examinations = examinationService.getListByIds(examRecordList.stream().map(ExaminationRecord::getExaminationId).distinct().toArray(Long[]::new));
        examRecordList.forEach(tempExamRecord -> {
            // 找到考试记录所属的考试信息
            Examination examinationRecordExamination = examinations.stream()
                    .filter(tempExamination -> tempExamRecord.getExaminationId().equals(tempExamination.getId()))
                    .findFirst().orElse(null);
            // 转换成ExamRecordDto
            if (examinationRecordExamination != null) {
                ExaminationRecordVO examRecordDto = new ExaminationRecordVO();
                BeanUtils.copyProperties(examinationRecordExamination, examRecordDto);
                examRecordDto.setId(tempExamRecord.getId());
                examRecordDto.setStartTime(tempExamRecord.getStartTime());
                examRecordDto.setEndTime(tempExamRecord.getEndTime());
                examRecordDto.setScore(tempExamRecord.getScore());
                examRecordDto.setUserId(tempExamRecord.getUserId());
                examRecordDto.setExaminationId(tempExamRecord.getExaminationId());
                // 正确题目数
                examRecordDto.setCorrectNumber(tempExamRecord.getCorrectNumber());
                examRecordDto.setInCorrectNumber(tempExamRecord.getIncorrectNumber());
                // 提交状态
                examRecordDto.setSubmitStatus(tempExamRecord.getSubmitStatus());
                examRecordVOList.add(examRecordDto);
            }
        });
        //this.fillExamUserInfo(examRecordDtoList, examRecordList.stream().map(ExaminationRecord::getUserId).distinct().toArray(Long[]::new));


        return examRecordVOList;
    }

    /**
     * 新增考试记录
     *
     * @param examinationRecord 考试记录
     * @return 结果
     */
    @Override
    public int insertExaminationRecord(ExaminationRecord examinationRecord) {
        examinationRecord.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(), SecurityUtils.getTenantCode());
        return examinationRecordMapper.insertExaminationRecord(examinationRecord);
    }

    /**
     * 修改考试记录
     *
     * @param examinationRecord 考试记录
     * @return 结果
     */
    @Override
    public int updateExaminationRecord(ExaminationRecord examinationRecord) {
        return examinationRecordMapper.updateExaminationRecord(examinationRecord);
    }

    /**
     * 批量删除考试记录
     *
     * @param ids 需要删除的考试记录主键
     * @return 结果
     */
    @Override
    public int deleteExaminationRecordByIds(Long[] ids) {
        return examinationRecordMapper.deleteExaminationRecordByIds(ids);
    }

    /**
     * 删除考试记录信息
     *
     * @param id 考试记录主键
     * @return 结果
     */
    @Override
    public int deleteExaminationRecordById(Long id) {
        return examinationRecordMapper.deleteExaminationRecordById(id);
    }

    /**
     * 根据时间范围查询考试记录数
     *
     * @param start start
     * @return List
     * @author tangyi
     * @date 2020/1/31 10:17 下午
     */
    public List<ExaminationRecord> findExaminationRecordCountByDate(Date start) {
        return examinationRecordMapper.findExaminationRecordCountByDate(start);
    }

    /**
     * 成绩详情
     *
     * @param id id
     * @return ExaminationRecordDto
     * @author tangyi
     * @date 2020/2/21 9:26 上午
     */
    public ExaminationRecordVO details(Long id) {
        ExaminationRecord examRecord = this.selectExaminationRecordById(id);
        if (examRecord == null)
            throw new ServiceException("ExamRecord is not exist");
        Examination examination = examinationService.selectExaminationById(examRecord.getExaminationId());
        if (examination == null)
            throw new ServiceException("Examination is not exist");
        ExaminationRecordVO examRecordVO = new ExaminationRecordVO();
        BeanUtils.copyProperties(examination, examRecordVO);
        examRecordVO.setId(examRecord.getId());
        examRecordVO.setStartTime(examRecord.getStartTime());
        examRecordVO.setEndTime(examRecord.getEndTime());
        examRecordVO.setScore(examRecord.getScore());
        examRecordVO.setUserId(examRecord.getUserId());
        examRecordVO.setUserName(examRecord.getUserName());
        examRecordVO.setExaminationId(examRecord.getExaminationId());
        examRecordVO.setDuration(
                DateUtils.getDatePoor(examRecord.getStartTime(), examRecord.getEndTime()));
        // 正确题目数
        examRecordVO.setCorrectNumber(examRecord.getCorrectNumber());
        examRecordVO.setInCorrectNumber(examRecord.getIncorrectNumber());
        // 提交状态
        examRecordVO.setSubmitStatus(examRecord.getSubmitStatus());
        // 答题列表
        Answer queryAnswer = new Answer();
        queryAnswer.setExamRecordId(examRecord.getId());
        List<Answer> answers = answerMapper.selectAnswerList(queryAnswer);
        if (CollectionUtils.isNotEmpty(answers)) {
            List<AnswerVO> answerDtos = answers.stream().map(answer -> {
                AnswerVO answerDto = new AnswerVO();
                BeanUtils.copyProperties(answer, answerDto);
                SubjectVO subjectDto = subjectService.getSubject(answer.getSubjectId(), answer.getType());
                answerDto.setSubject(subjectDto);
                answerDto.setDuration(ExamRecordUtils.getExamDuration(answer.getStartTime(), answer.getEndTime()));
                return answerDto;
            }).collect(Collectors.toList());
            examRecordVO.setAnswers(answerDtos);
        }
        return examRecordVO;
    }

    /**
     * 查询参与考试人数
     *
     * @return ExaminationDashboardDto
     * @author tangyi
     * @date 2019/10/27 20:07:38
     */
    @Override
    public ExaminationDashboardVO findExamDashboardData(String tenantCode) {
        ExaminationDashboardVO dashboardDto = new ExaminationDashboardVO();
        Examination examination = new Examination();
        examination.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(), tenantCode);
        examination.setStatus(Constants.NORMAL);
        // 试卷数量
        dashboardDto.setExaminationCount(examinationService.findExaminationCount(examination));
        // 考生数量
        dashboardDto.setExamUserCount(examinationService.findExamUserCount(examination));

        //题目数
        ExaminationSubject examinationSubject = new ExaminationSubject();
        examinationSubject.setTenantCode(tenantCode);
        dashboardDto.setSubjectCount(examinationSubjectService.findSubjectCount(examinationSubject));

        //课程数
        Course course = new Course();
        course.setTenantCode(tenantCode);
        dashboardDto.setCourseCount(courseService.findCourseCount(course));

        // 考试记录数量
        ExaminationRecord examinationRecord = new ExaminationRecord();
        examinationRecord.setCommonValue(examination.getCreator(), examination.getApplicationCode(), examination.getTenantCode());
        examinationRecord.setCreateDate(null);
        examinationRecord.setModifyDate(null);
        dashboardDto.setExaminationRecordCount(examinationRecordMapper.findExaminationRecordCount(examinationRecord));
        return dashboardDto;
    }

    /**
     * 查询过去n天的考试记录数据
     *
     * @param tenantCode tenantCode
     * @param pastDays   pastDays
     * @return ExaminationDashboardDto
     * @author tangyi
     * @date 2020/1/31 5:46 下午
     */
    @Override
    public ExaminationDashboardVO findExamRecordTendency(String tenantCode, Integer pastDays) {
        ExaminationDashboardVO dashboardDto = new ExaminationDashboardVO();
        Examination examination = new Examination();
        examination.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(), tenantCode);
        Map<String, String> tendencyMap = new LinkedHashMap<>();
        LocalDateTime start = null;
        pastDays = -pastDays;
        for (int i = pastDays; i <= 0; i++) {
            LocalDateTime localDateTime = DateUtils.plusDay(i);
            if (i == pastDays) {
                start = localDateTime;
            }
            tendencyMap.put(localDateTime.format(DateUtils.FORMATTER_DAY), "0");
        }
        List<ExaminationRecord> examinationRecords = this.findExaminationRecordCountByDate(DateUtils.asDate(start));
        if (CollectionUtils.isNotEmpty(examinationRecords)) {
            Map<String, List<ExaminationRecord>> examinationRecordsMap = examinationRecords.stream()
                    .peek(examinationRecord -> examinationRecord
                            .setExt(DateUtils.asLocalDateTime(examinationRecord.getCreateDate())
                                    .format(DateUtils.FORMATTER_DAY)))
                    .collect(Collectors.groupingBy(ExaminationRecord::getExt));
            log.info("ExamRecordTendency map: {}", examinationRecordsMap);
            examinationRecordsMap.forEach((key, value) -> tendencyMap.replace(key, String.valueOf(value.size())));
        }
        dashboardDto.setExamRecordDate(new ArrayList<>(tendencyMap.keySet()));
        dashboardDto.setExamRecordData(new ArrayList<>(tendencyMap.values()));
        return dashboardDto;
    }


}
