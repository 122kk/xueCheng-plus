package com.xuecheng.content.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.dto.CourseTeacherDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kj
 * @date 2023/3/16
 * @apiNote
 */
@RestController
@Slf4j
public class CourseTeacherController {
    @Autowired
    CourseTeacherService courseTeacherService;

    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacherDto> queryTeacher(@PathVariable Long courseId){
        return courseTeacherService.queryTeacher(courseId);
    }

    @PostMapping("/courseTeacher")
    public void saveTeacher(@RequestBody CourseTeacher teacher){
        boolean b = courseTeacherService.saveOrUpdate(teacher);
    }

    @DeleteMapping("/courseTeacher/course/{courseId}/{id}")
    public void deleteTeacher(@PathVariable Long id,@PathVariable Long courseId){
        courseTeacherService.deleteTeacher(id, courseId);
    }

}
