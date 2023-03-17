package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.dto.CourseTeacherDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author kj
 * @date 2023/3/16
 * @apiNote
 */
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper,CourseTeacher> implements CourseTeacherService {
    @Autowired
    CourseTeacherMapper courseTeacherMapper;

    @Override
    public List<CourseTeacherDto> queryTeacher(Long courseId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper=
                new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId,courseId);
        List<CourseTeacher> courseTeachers = courseTeacherMapper.selectList(queryWrapper);

        List<CourseTeacherDto> courseTeacherDtos=new ArrayList<>();

        for (CourseTeacher teacher:courseTeachers){
            CourseTeacherDto courseTeacher=new CourseTeacherDto();
            BeanUtils.copyProperties(teacher,courseTeacher);
            courseTeacherDtos.add(courseTeacher);
        }
        return courseTeacherDtos;
    }

    @Override
    public void deleteTeacher(Long id, Long courseId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(CourseTeacher::getId,id)
                .eq(CourseTeacher::getCourseId,courseId);
        courseTeacherMapper.delete(queryWrapper);
    }
}
