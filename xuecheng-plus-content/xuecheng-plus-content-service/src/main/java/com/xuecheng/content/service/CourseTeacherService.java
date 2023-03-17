package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.content.model.dto.CourseTeacherDto;
import com.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

/**
 * @author kj
 * @date 2023/3/16
 * @apiNote
 */
public interface CourseTeacherService extends IService<CourseTeacher> {
    /**
     * 查询课程相关的老师
     * @param courseId 课程标识
     * @return
     */
    public List<CourseTeacherDto> queryTeacher(Long courseId);

    /**
     * 删除课程相关的老师
     * @param id id
     * @param courseId 课程标识
     */
    public void deleteTeacher(Long id,Long courseId);

}
