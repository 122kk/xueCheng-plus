package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

/**
 * @author kj
 * @date 2023/3/15
 * @apiNote
 */
public interface TeachplanService {
    public List<TeachplanDto> findTeachplayTree(Long courseId);

    /**
     * 保存课程计划（新增、修改）
     * @param dto
     */
    public void saveTeachplan(SaveTeachplanDto dto);
}
