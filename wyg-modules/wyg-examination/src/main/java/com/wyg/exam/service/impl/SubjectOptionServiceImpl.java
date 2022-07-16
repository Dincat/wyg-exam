package com.wyg.exam.service.impl;

import java.util.List;

import com.wyg.common.security.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.exam.mapper.SubjectOptionMapper;
import com.wyg.exam.domain.SubjectOption;
import com.wyg.exam.service.ISubjectOptionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 选择题选项Service业务层处理
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Service
@AllArgsConstructor
public class SubjectOptionServiceImpl implements ISubjectOptionService 
{
    @Autowired
    private SubjectOptionMapper subjectOptionMapper;

    /**
     * 查询选择题选项
     * 
     * @param id 选择题选项主键
     * @return 选择题选项
     */
    @Override
    public SubjectOption selectSubjectOptionById(Long id)
    {
        return subjectOptionMapper.selectSubjectOptionById(id);
    }

    /**
     * 查询选择题选项列表
     * 
     * @param subjectOption 选择题选项
     * @return 选择题选项
     */
    @Override
    public List<SubjectOption> selectSubjectOptionList(SubjectOption subjectOption)
    {
        return subjectOptionMapper.selectSubjectOptionList(subjectOption);
    }

    /**
     * 新增选择题选项
     * 
     * @param subjectOption 选择题选项
     * @return 结果
     */
    @Override
    public int insertSubjectOption(SubjectOption subjectOption)
    {
        subjectOption.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        return subjectOptionMapper.insertSubjectOption(subjectOption);
    }

    /**
     * 修改选择题选项
     * 
     * @param subjectOption 选择题选项
     * @return 结果
     */
    @Override
    public int updateSubjectOption(SubjectOption subjectOption)
    {
        subjectOption.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        return subjectOptionMapper.updateSubjectOption(subjectOption);
    }

    /**
     * 批量删除选择题选项
     * 
     * @param ids 需要删除的选择题选项主键
     * @return 结果
     */
    @Override
    public int deleteSubjectOptionByIds(Long[] ids)
    {
        return subjectOptionMapper.deleteSubjectOptionByIds(ids);
    }

    /**
     * 删除选择题选项信息
     *
     * @param id 选择题选项主键
     * @return 结果
     */
    @Override
    public int deleteSubjectOptionById(Long id)
    {
        return subjectOptionMapper.deleteSubjectOptionById(id);
    }

    @Override
    public int deleteBySubjectChoicesId(SubjectOption subjectOption) {
        return subjectOptionMapper.deleteBySubjectChoicesId(subjectOption);
    }

    /**
     * 批量保存
     *
     * @param subjectOptionList subjectOptionList
     * @return int
     * @author tangyi
     * @date 2019/6/16 15:01
     */
    @Transactional
    public int insertBatch(List<SubjectOption> subjectOptionList) {
        return subjectOptionMapper.insertBatch(subjectOptionList);
    }



}
