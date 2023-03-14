package com.xuecheng;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.content.service.CourseCategoryService;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author kj
 * @date 2023/3/13
 * @apiNote
 */
@SpringBootTest
public class CourseBaseMapperTests {
    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @Autowired
    CourseCategoryService courseCategoryService;

    @Test
    void testCourseBaseMapper(){
        CourseBase courseBase=
                courseBaseMapper.selectById(74L);
        Assertions.assertNotNull(courseBase);
        System.out.println(courseBase);
    }

    @Test
    void testCourseBaseService(){
        PageParams params=new PageParams();
        PageResult<CourseBase> courseBasePageResult=
                courseBaseInfoService.queryCourseBaseList(params,new QueryCourseParamsDto());
        System.out.println(courseBasePageResult);
    }

    @Test
    void testqueryTreeNodes(){
        List<CourseCategoryTreeDto> categoryTreeDtos=
                courseCategoryService.queryTreeNodes("1");
        System.out.println(categoryTreeDtos);
    }
}
