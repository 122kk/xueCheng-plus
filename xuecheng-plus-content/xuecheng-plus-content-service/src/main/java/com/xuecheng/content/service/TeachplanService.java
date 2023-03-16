package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;

import java.util.List;

/**
 * @author kj
 * @date 2023/3/15
 * @apiNote
 */
public interface TeachplanService extends IService<Teachplan> {
    public List<TeachplanDto> findTeachplayTree(Long courseId);

    /**
     * 保存课程计划（新增、修改）
     * @param dto
     */
    public void saveTeachplan(SaveTeachplanDto dto);

    /**
     * 查询当前信息的下一条信息
     * @param id
     */
    public void updateDownTeachplan(Long id);

    /**
     * 查询当前信息的上一条信息
     * @param id
     */
    public void updateUpTeachplan(Long id);
}
