package com.jdh.mapper;

import com.jdh.pojo.BackgroundSpeDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 背景特效 mapper
 */
@Mapper
public interface BackgroundSpeMapper {


    /**
     * 获取所有特效
     * @param map
     * map 参数有 :
     * Boolean transparent 是否透明  必选
     * order key 字段名 val asc/desc 根据某字段排序 可选
     *
     * @return
     */
    public List<BackgroundSpeDo> getBackgroundSpeByTransparent(Map map);


}
