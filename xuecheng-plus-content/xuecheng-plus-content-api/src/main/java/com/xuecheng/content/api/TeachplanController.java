package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kj
 * @date 2023/3/15
 * @apiNote
 */
@RestController
@Slf4j
public class TeachplanController {

    @Autowired
    TeachplanService teachplanService;

    // 获取课程计划
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
        return teachplanService.findTeachplayTree(courseId);
    }

    // 添加课程计划
    @PostMapping("/teachplan")
    public void saveTeachplan(@RequestBody SaveTeachplanDto dto){
        teachplanService.saveTeachplan(dto);
    }

    //删除课程计划
    @DeleteMapping("/teachplan/{id}")
    public void deleteTeachplan(@PathVariable Long id){
        teachplanService.removeById(id);
    }

    @PostMapping("teachplan/movedown/{id}")
    public void updateDownTeachplan(@PathVariable Long id){
        teachplanService.updateDownTeachplan(id);
    }

    @PostMapping("teachplan/moveup/{id}")
    public void updateUpTeachplan(@PathVariable Long id){
        teachplanService.updateUpTeachplan(id);
    }
}
