package com.wyg.exam.service.impl;

import java.util.Date;
import java.util.List;

import com.wyg.exam.domain.Examination;
import com.wyg.exam.mapper.ExamPaperRatesMapper;
import com.wyg.exam.mapper.ExaminationMapper;
import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.core.utils.StringUtils;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.system.api.RemoteUserService;
import com.wyg.system.api.domain.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.exam.domain.ExamPaperRates;
import com.wyg.exam.service.IExamPaperRatesService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 试卷评价Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-05-30
 */
@Service
public class ExamPaperRatesServiceImpl implements IExamPaperRatesService {
    @Autowired
    private ExamPaperRatesMapper examPaperRatesMapper;

    @Autowired
    private ExaminationMapper examinationMapper;

    @Autowired
    RemoteUserService remoteUserService;

    /**
     * 查询试卷评价
     *
     * @param id 试卷评价主键
     * @return 试卷评价
     */
    @Override
    public ExamPaperRates selectExamPaperRatesById(Long id) {
        return examPaperRatesMapper.selectExamPaperRatesById(id);
    }

    /**
     * 查询试卷评价列表
     *
     * @param examPaperRates 试卷评价
     * @return 试卷评价
     */
    @Override
    public List<ExamPaperRates> selectExamPaperRatesList(ExamPaperRates examPaperRates) {
        List<ExamPaperRates> list= examPaperRatesMapper.selectExamPaperRatesList(examPaperRates);

        for(ExamPaperRates rate:list){
          SysUser sysUser= remoteUserService.findById(rate.getUserId(), SecurityConstants.INNER).getData();
          if(sysUser==null){
              rate.setUserName("--未知用户--");
              continue;
          }

            rate.setUserName(StringUtils.mask(sysUser.getNickName()));
        }

        return list;
    }

    /**
     * 新增试卷评价
     *
     * @param examPaperRates 试卷评价
     * @return 结果
     */
    @Override
    public int insertExamPaperRates(ExamPaperRates examPaperRates) {
        examPaperRates.setCreateTime(DateUtils.getNowDate());
        return examPaperRatesMapper.insertExamPaperRates(examPaperRates);
    }

    /**
     * 修改试卷评价
     *
     * @param examPaperRates 试卷评价
     * @return 结果
     */
    @Override
    public int updateExamPaperRates(ExamPaperRates examPaperRates) {
        examPaperRates.setUpdateTime(DateUtils.getNowDate());
        return examPaperRatesMapper.updateExamPaperRates(examPaperRates);
    }

    /**
     * 批量删除试卷评价
     *
     * @param ids 需要删除的试卷评价主键
     * @return 结果
     */
    @Override
    public int deleteExamPaperRatesByIds(Long[] ids) {
        return examPaperRatesMapper.deleteExamPaperRatesByIds(ids);
    }

    /**
     * 删除试卷评价信息
     *
     * @param id 试卷评价主键
     * @return 结果
     */
    @Override
    public int deleteExamPaperRatesById(Long id) {
        return examPaperRatesMapper.deleteExamPaperRatesById(id);
    }


    @Override
    @Transactional
    public Double rate(ExamPaperRates examPaperRates) {
        Double avgRating = new Double(0.00);

        if (examPaperRates == null || examPaperRates.getPaperId() == null || examPaperRates.getPaperId().longValue() < 1 ||
                examPaperRates.getRating() == null || examPaperRates.getRating().longValue() < 1) {
            return avgRating;
        }

        ExamPaperRates query = new ExamPaperRates();
        query.setUserId(examPaperRates.getUserId());
        query.setPaperId(examPaperRates.getPaperId());
        List<ExamPaperRates> ratesList = selectExamPaperRatesList(query);

        examPaperRates.setUpdateTime(new Date());

        int result = 0;
        if (CollectionUtils.isNotEmpty(ratesList)) {
            examPaperRates.setId(ratesList.get(0).getId());
            examPaperRates.setCreateBy(SecurityUtils.getUserId() + "");
            result = this.updateExamPaperRates(examPaperRates);
        } else {
            examPaperRates.setUpdateBy(SecurityUtils.getUserId() + "");
            examPaperRates.setCreateBy(SecurityUtils.getUsername());
            examPaperRates.setCreateTime(examPaperRates.getUpdateTime());
            result = this.insertExamPaperRates(examPaperRates);
        }


        if (result > 0) {
            //更新试卷评分值
            avgRating = examPaperRatesMapper.selectRatingAvgByPaperId(examPaperRates.getPaperId());
            if (avgRating != null && avgRating.doubleValue() > 0) {
                Examination paper = new Examination();
                paper.setId(examPaperRates.getPaperId());
                paper.setRating(avgRating);
                examinationMapper.updateRate(paper);
            }
        }

        return avgRating;

    }
}
