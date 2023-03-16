package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.execption.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kj
 * @date 2023/3/15
 * @apiNote
 */
@Service
@Slf4j
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper,Teachplan> implements TeachplanService {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Autowired
    TeachplanService teachplanService;

    @Override
    public List<TeachplanDto> findTeachplayTree(Long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    //新增、修改
    @Override
    public void saveTeachplan(SaveTeachplanDto dto) {

        Long id = dto.getId();

        Teachplan teachplan = teachplanMapper.selectById(id);

        if(teachplan==null){
            teachplan = new Teachplan();
            BeanUtils.copyProperties(dto,teachplan);
            //找到同级课程计划的数量
            int count = getTeachplanCount(dto.getCourseId(), dto.getParentid());
            //新课程计划的值
            teachplan.setOrderby(count+1);

            teachplanMapper.insert(teachplan);

        }else{
            BeanUtils.copyProperties(dto,teachplan);
            //更新
            teachplanMapper.updateById(teachplan);

        }


    }

    @Transactional
    @Override
    public void updateDownTeachplan(Long id) {
        // 获取当前数据
        Teachplan teachplan = teachplanService.getById(id);
        //查询当前数据的下一条数据
        Teachplan downTeachplan = teachplanMapper.selectDownTeachplan(teachplan);
        if(downTeachplan==null){
            throw new XueChengPlusException("当前数据为最后一条数据无法下移");
        }
        // 设置当前数据的orderby为下一条数据的orderby
        int num=downTeachplan.getOrderby();
        downTeachplan.setOrderby(teachplan.getOrderby());
        teachplanService.saveOrUpdate(downTeachplan);
        teachplan.setOrderby(num);
        teachplanService.saveOrUpdate(teachplan);

        // log.info("teachplan1:{}",teachplan1);
    }

    @Transactional
    @Override
    public void updateUpTeachplan(Long id) {
        Teachplan teachplan = teachplanService.getById(id);
        //查询当前数据的上一条数据
        Teachplan upTeachplan = teachplanMapper.selectUpTeachplan(teachplan);
        if(upTeachplan==null){
            throw new XueChengPlusException("当前数据为第一条数据无法上移");
        }
        // 设置当前数据的orderby为下一条数据的orderby
        int num=upTeachplan.getOrderby();
        upTeachplan.setOrderby(teachplan.getOrderby());
        teachplanService.saveOrUpdate(upTeachplan);
        teachplan.setOrderby(num);
        teachplanService.saveOrUpdate(teachplan);
    }


    //计算机新课程计划的orderby 找到同级课程计划的数量 SELECT count(1) from teachplan where course_id=117 and parentid=268
    public int getTeachplanCount(Long courseId,Long parentId){

        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId,courseId);
        queryWrapper.eq(Teachplan::getParentid,parentId);
        Integer count = teachplanMapper.selectCount(queryWrapper);
        return count.intValue();

    }
}
