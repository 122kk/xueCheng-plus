package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author kj
 * @date 2023/3/13
 * @apiNote 课程基本信息管理业务接口
 */
public interface CourseBaseInfoService {
    /**
     * @description 课程查询接口
     * @param pageParams 分页参数（pageNo，pagesize）
     * @param queryCourseParamsDto 条件
     * @return
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * 新增课程
     * @param companyId 培训机构ID
     * @param dto 新增课程的信息
     * @return 课程信息
     */
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto dto);

    /**
     * 修改课程信息
     * @param companyId 机构id，要校验本机构只能修改本机构的课程
     * @param dto 课程信息
     * @return 课程信息
     */
    public CourseBaseInfoDto updateCourseBase(Long companyId,EditCourseDto dto);

    /**
     * 根据id查询课程信息
     * @param courseId 课程id
     * @return 课程信息
     */
    public CourseBaseInfoDto getCourseBaseInfo(Long courseId);

    /**
     * 根据id删除课程
     * @param courseId 课程id
     */
    public void deleteCourseBaseInfo(Long courseId);
}
