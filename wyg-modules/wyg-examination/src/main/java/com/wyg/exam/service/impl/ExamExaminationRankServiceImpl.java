package com.wyg.exam.service.impl;

import java.util.Date;
import java.util.List;

import com.wyg.common.core.utils.JsonUtil;
import com.wyg.exam.constants.AnswerConstant;
import com.wyg.exam.domain.ExaminationRecord;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.redis.service.RedisService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.exam.mapper.ExamExaminationRankMapper;
import com.wyg.exam.domain.ExamExaminationRank;
import com.wyg.exam.service.IExamExaminationRankService;

/**
 * 考试成绩排行Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-05-25
 */
@Service
public class ExamExaminationRankServiceImpl implements IExamExaminationRankService {
    @Autowired
    private ExamExaminationRankMapper examExaminationRankMapper;

    @Autowired
    RedisService redisService;

    /**
     * 查询考试成绩排行
     *
     * @param id 考试成绩排行主键
     * @return 考试成绩排行
     */
    @Override
    public ExamExaminationRank selectExamExaminationRankById(Long id) {
        return examExaminationRankMapper.selectExamExaminationRankById(id);
    }

    /**
     * 查询考试成绩排行列表
     *
     * @param examExaminationRank 考试成绩排行
     * @return 考试成绩排行
     */
    @Override
    public List<ExamExaminationRank> selectExamExaminationRankList(ExamExaminationRank examExaminationRank) {
        return examExaminationRankMapper.selectExamExaminationRankList(examExaminationRank);
    }

    /**
     * 新增考试成绩排行
     *
     * @param examExaminationRank 考试成绩排行
     * @return 结果
     */
    @Override
    public int insertExamExaminationRank(ExamExaminationRank examExaminationRank) {
        examExaminationRank.setCreateTime(DateUtils.getNowDate());
        return examExaminationRankMapper.insertExamExaminationRank(examExaminationRank);
    }

    /**
     * 修改考试成绩排行
     *
     * @param examExaminationRank 考试成绩排行
     * @return 结果
     */
    @Override
    public int updateExamExaminationRank(ExamExaminationRank examExaminationRank) {
        examExaminationRank.setUpdateTime(DateUtils.getNowDate());
        return examExaminationRankMapper.updateExamExaminationRank(examExaminationRank);
    }

    /**
     * 批量删除考试成绩排行
     *
     * @param ids 需要删除的考试成绩排行主键
     * @return 结果
     */
    @Override
    public int deleteExamExaminationRankByIds(Long[] ids) {
        return examExaminationRankMapper.deleteExamExaminationRankByIds(ids);
    }

    /**
     * 删除考试成绩排行信息
     *
     * @param id 考试成绩排行主键
     * @return 结果
     */
    @Override
    public int deleteExamExaminationRankById(Long id) {
        return examExaminationRankMapper.deleteExamExaminationRankById(id);
    }


    /**
     * 更新排名
     * @param examinationRecord
     * @return
     */
    @Override
    public int updateRank(ExaminationRecord examinationRecord) {

        if (examinationRecord == null || examinationRecord.getExaminationId() == null || examinationRecord.getExaminationId().longValue() < 1 ||
                examinationRecord.getUserId() == null || examinationRecord.getUserId().longValue() < 1) return 0;

        ExamExaminationRank query = new ExamExaminationRank();
        query.setPaperId(examinationRecord.getExaminationId());
        query.setUserId(examinationRecord.getUserId());
        List<ExamExaminationRank> rankList = selectExamExaminationRankList(query);

        ExamExaminationRank rank = new ExamExaminationRank();
        if (CollectionUtils.isNotEmpty(rankList)) rank = rankList.get(0);

        rank.setPaperId(examinationRecord.getExaminationId());
        rank.setUserId(examinationRecord.getUserId());
        rank.setUserName(examinationRecord.getUserName());
        rank.setScore(examinationRecord.getScore());
        rank.setCorrectNumber(new Long(examinationRecord.getCorrectNumber()));
        rank.setIncorrectNumber(new Long(examinationRecord.getIncorrectNumber()));
        rank.setUpdateTime(new Date());
        rank.setElapsedTime(DateUtils.getDatePoor(examinationRecord.getStartTime(),examinationRecord.getEndTime()));

        if (rank.getId() == null || rank.getId() < 1) {
            rank.setCreateTime(new Date());
            insertExamExaminationRank(rank);
        } else {
            updateExamExaminationRank(rank);
        }

        redisService.addForZSet(AnswerConstant.CACHE_PREFIX_RANK + examinationRecord.getExaminationId(), JsonUtil.toJson(examinationRecord), examinationRecord.getScore());
        return 1;
    }

    public List<ExamExaminationRank> selectRankList(ExamExaminationRank examExaminationRank){
        return examExaminationRankMapper.selectRankList(examExaminationRank);
    }
}
