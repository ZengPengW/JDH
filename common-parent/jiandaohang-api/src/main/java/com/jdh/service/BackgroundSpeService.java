package com.jdh.service;

import com.jdh.pojo.BackgroundSpeDo;
import com.jdh.utils.PageDataGridResult;

import java.util.List;
import java.util.Map;

public interface BackgroundSpeService {

    /**
     * 获取所有特效
     * @param transparent 是否透明
     * @param page 第几页
     * @param size 每页几条
     * @param field 排序字段名
     * @param order asc/desc 排序规则
     * @return
     */
    public PageDataGridResult<BackgroundSpeDo> getBackgroundSpeByTransparent(Boolean transparent,Integer page,Integer size,String field,String order);

    /**
     * 根据sid查询背景特效
     * @param sid
     * @return
     */
    public BackgroundSpeDo getBackgroundSpeBySid(Long sid);
}
