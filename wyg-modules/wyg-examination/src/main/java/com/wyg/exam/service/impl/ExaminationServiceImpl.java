package com.wyg.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wyg.exam.config.ResourcesConfig;
import com.wyg.exam.domain.*;
import com.wyg.exam.domain.vo.*;
import com.wyg.exam.mapper.ExamExaminationRankMapper;
import com.wyg.exam.service.*;
import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.utils.StringUtils;
import com.wyg.common.security.utils.SecurityUtils;
import com.wyg.file.api.domain.SysMaterial;
import com.wyg.file.api.RemoteFileService;
//import com.wyg.system.api.domain.SysMaterial;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.exam.mapper.ExaminationMapper;

/**
 * 考试信息Service业务层处理
 *
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Service
public class ExaminationServiceImpl implements IExaminationService {
    @Autowired
    private ExaminationMapper examinationMapper;

    @Autowired
    RemoteFileService remoteFileService;

    @Autowired
    ResourcesConfig resourcesConfig;

    @Autowired
    SubjectService subjectService;

    @Autowired
    ISubjectChoicesService choicesService;

    @Autowired
    ISubjectOptionService optionService;

    @Autowired
    ISubjectJudgementService judgementService;

    @Autowired
    ISubjectShortAnswerService shortAnswerService;

    @Autowired
    IExamExaminationRankService examinationRankService;

    @Autowired
    ExamExaminationRankMapper examExaminationRankMapper;


    /**
     * 查询考试信息
     *
     * @param id 考试信息主键
     * @return 考试信息
     */
    @Override
    public PaperVO selectExaminationById(Long id) {
        Examination exam = examinationMapper.selectExaminationById(id);

        PaperVO paper = new PaperVO();
        BeanUtils.copyProperties(exam, paper);

        if (paper.getAvatarId() == null || paper.getAvatarId().longValue() < 1) return paper;

        SysMaterial material = remoteFileService.getMaterialInfo(paper.getAvatarId(), SecurityConstants.INNER).getData();
        if (material == null || StringUtils.isEmpty(material.getMaterialUrl())) return paper;
        String avatar ="";// material.getMaterialUrl();

        paper.setLogoUrl(avatar);

//        ExamExaminationRank rankQuery = new ExamExaminationRank();
//        rankQuery.setPaperId(exam.getId());
//        List<ExamExaminationRank> rankList = examExaminationRankMapper.selectRankList(rankQuery);
//        paper.setRankList(rankList);

        return paper;
    }

    /**
     * 查询考试信息列表
     *
     * @param examination 考试信息
     * @return 考试信息
     */
    @Override
    public List<Examination> selectExaminationList(Examination examination) {
        List<Examination> list = examinationMapper.selectExaminationList(examination);
        for (Examination exam : list) {
            if (exam.getAvatarId() == null || exam.getAvatarId().longValue() < 1) continue;

            SysMaterial material = remoteFileService.getMaterialInfo(exam.getAvatarId(), SecurityConstants.INNER).getData();
            if (material == null || StringUtils.isEmpty(material.getMaterialUrl())) continue;
            String avatar ="";// material.getMaterialUrl();

            exam.setLogoUrl(avatar);
        }

        return list;
    }

    /**
     * 新增考试信息
     *
     * @param examination 考试信息
     * @return 结果
     */
    @Override
    public int insertExamination(Examination examination) {
        return examinationMapper.insertExamination(examination);
    }

    /**
     * 修改考试信息
     *
     * @param examination 考试信息
     * @return 结果
     */
    @Override
    public int updateExamination(Examination examination) {
        examination.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(), SecurityUtils.getTenantCode());
        return examinationMapper.updateExamination(examination);
    }

    /**
     * 批量删除考试信息
     *
     * @param ids 需要删除的考试信息主键
     * @return 结果
     */
    @Override
    public int deleteExaminationByIds(Long[] ids) {
        return examinationMapper.deleteExaminationByIds(ids);
    }

    /**
     * 删除考试信息信息
     *
     * @param id 考试信息主键
     * @return 结果
     */
    @Override
    public int deleteExaminationById(Long id) {
        return examinationMapper.deleteExaminationById(id);
    }

    @Override
    public List<Examination> getListByIds(Long[] ids) {
        if (ids == null || ids.length == 0)
            return new ArrayList<>();

        return examinationMapper.getListByIds(ids);
    }

    @Override
    public Integer findExaminationCount(Examination examination) {
        return examinationMapper.findExaminationCount(examination);
    }

    @Override
    public Integer findExamUserCount(Examination examination) {
        return 0;//examinationMapper.findExamUserCount(examination);
    }


    //region 词汇造题



    //endregion


    @Override
    public int updateRate(Examination examination) {
        return examinationMapper.updateRate(examination);
    }
}
