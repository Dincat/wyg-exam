package com.wyg.exam.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.wyg.common.core.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wyg.common.log.annotation.Log;
import com.wyg.common.log.enums.BusinessType;
import com.wyg.common.security.annotation.RequiresPermissions;
import com.wyg.exam.domain.Course;
import com.wyg.exam.service.ICourseService;
import com.wyg.common.core.web.controller.BaseController;
import com.wyg.common.core.web.domain.AjaxResult;
import com.wyg.common.core.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.wyg.common.core.web.page.TableDataInfo;

/**
 * 课程Controller
 * 
 * @author WorrilessGo
 * @date 2022-04-06
 */
@Api(value = "课程",description="课程",tags = "课程")
@RestController
@RequestMapping("/course")
public class CourseController extends BaseController
{
    @Autowired
    private ICourseService courseService;

    /**
     * 查询课程列表
     */
    @RequiresPermissions("exam:course:list")
    @GetMapping("/list")
    @ApiOperation( value = "获取课程列表",notes = "",httpMethod = "GET" )
    public TableDataInfo list(Course course)
    {
        startPage();
        List<Course> list = courseService.selectCourseList(course);
        return getDataTable(list);
    }


    /**
     * 导出课程列表
     */
    @RequiresPermissions("exam:course:export")
    @Log(title = "导出课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation( value = "导出课程列表",notes = "",httpMethod = "POST" )
    public void export(HttpServletResponse response, Course course) throws IOException
    {
        List<Course> list = courseService.selectCourseList(course);
        ExcelUtil<Course> util = new ExcelUtil<Course>(Course.class);
        util.exportExcel(response, list, "课程数据");
    }

    /**
     * 获取课程详细信息
     */
    @RequiresPermissions("exam:course:query")
    @GetMapping(value = "/{id}")
    @ApiOperation( value = "获取课程列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(courseService.selectCourseById(id));
    }

    /**
     * 新增课程
     */
    @RequiresPermissions("exam:course:add")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation( value = "新增课程",notes = "",httpMethod = "POST" )
    public AjaxResult add(@RequestBody Course course)
    {
        return toAjax(courseService.insertCourse(course));
    }

    /**
     * 修改课程
     */
    @RequiresPermissions("exam:course:edit")
    @Log(title = "修改课程", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation( value = "修改课程",notes = "",httpMethod = "PUT" )
    public AjaxResult edit(@RequestBody Course course)
    {
        return toAjax(courseService.updateCourse(course));
    }

    /**
     * 删除课程
     */
    @RequiresPermissions("exam:course:remove")
    @Log(title = "删除课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation( value = "批量删除课程",notes = "删除单个或多个课程，传入ID数组。",httpMethod = "DELETE" )
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(courseService.deleteCourseByIds(ids));
    }


    /**
     * 查询课程列表
     */
    @GetMapping("/noauth/list")
    @ApiOperation( value = "获取课程列表",notes = "",httpMethod = "GET" )
    public TableDataInfo webList(Course course)
    {
        if(course==null) course=new Course();

        course.setDelFlag(Constants.NORMAL);

        startPage();
        List<Course> list = courseService.selectCourseList(course);
        return getDataTable(list);
    }

    /**
     * 获取课程详细信息
     */
    @GetMapping(value = "/noauth/{id}")
    @ApiOperation( value = "获取课程列详细信息",notes = "",httpMethod = "GET" )
    public AjaxResult getWebInfo(@PathVariable("id") Long id)
    {
        Course course=courseService.selectCourseById(id);
        if(course==null || !course.getDelFlag().equals(Constants.NORMAL)) return AjaxResult.error("课程信息不存在");

        return AjaxResult.success(course);
    }

}
