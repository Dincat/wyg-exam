package com.wyg.exam.service.impl;

import java.util.List;

import com.wyg.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wyg.exam.mapper.CourseMapper;
import com.wyg.exam.domain.Course;
import com.wyg.exam.service.ICourseService;

/**
 * 课程Service业务层处理
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Service
public class CourseServiceImpl implements ICourseService 
{
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询课程
     * 
     * @param id 课程主键
     * @return 课程
     */
    @Override
    public Course selectCourseById(Long id)
    {
        return courseMapper.selectCourseById(id);
    }

    /**
     * 查询课程列表
     * 
     * @param course 课程
     * @return 课程
     */
    @Override
    public List<Course> selectCourseList(Course course)
    {
        return courseMapper.selectCourseList(course);
    }

    /**
     * 新增课程
     * 
     * @param course 课程
     * @return 结果
     */
    @Override
    public int insertCourse(Course course)
    {
        course.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        return courseMapper.insertCourse(course);
    }

    /**
     * 修改课程
     * 
     * @param course 课程
     * @return 结果
     */
    @Override
    public int updateCourse(Course course)
    {
        course.setCommonValue(SecurityUtils.getUsername(), SecurityUtils.getApplicationCode(),SecurityUtils.getTenantCode());
        return courseMapper.updateCourse(course);
    }

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的课程主键
     * @return 结果
     */
    @Override
    public int deleteCourseByIds(Long[] ids)
    {
        return courseMapper.deleteCourseByIds(ids);
    }

    /**
     * 删除课程信息
     *
     * @param id 课程主键
     * @return 结果
     */
    @Override
    public int deleteCourseById(Long id)
    {
        return courseMapper.deleteCourseById(id);
    }

    @Override
    public int findCourseCount(Course course) {
        return courseMapper.findCourseCount(course);
    }
}
