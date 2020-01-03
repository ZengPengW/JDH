package com.jdh.mapper;

import com.jdh.pojo.MouseImgDo;
import com.jdh.pojo.MouseStyleDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 鼠标样式 mapper
 */
@Mapper
public interface MouseStyleMapper {

    /**
     * 增加鼠标图标
     * @param mouseImgDo
     * @return
     */
    public Integer addMouseImg(MouseImgDo mouseImgDo);

    /**
     * 保存用户鼠标样式映射
     * @param mouseStyleDo
     * @return
     */
    public Integer saveMouseStyleDo(MouseStyleDo mouseStyleDo);


    /**
     * 更新用户鼠标样式
     * @param mouseStyleDo
     * @return
     */
    public Integer updateMouseStyleDo(MouseStyleDo mouseStyleDo);

    /**
     * 根据MD5 查询图标
     * @param md5
     * @return
     */
    public List<MouseImgDo> getMouseImgDoByMd5(String md5);


    /**
     * 更新鼠标图标
     * @param mouseImgDo
     * @return
     */
    public Integer updateMouseImgDo(MouseImgDo mouseImgDo);

    /**
     * 根据uid 获取映射表
     * @param uid
     * @return
     */
    public MouseStyleDo getMouseStyleDoByUid(Integer uid);

    /**
     * 根据mid 删除图标
     * @param mid
     * @return
     */
    public Integer deleteMouseImgDoByMid(Long mid);

    /**
     * 查询我的上传图标
     * @param mouseType 0 指针 1 手指 or 2都可以
     * @param uid 用户id
     * @return
     */
    public List<MouseImgDo> getMouseImgDoByMyUpload(@Param("mouseType") Integer mouseType,@Param("uid") Integer uid);

    /**
     * map
     * 查询共享图标
     *  mouseType 0 指针 1 手指 or 2都可以
     *  share 是否共享 true false
     *  (field,order) 字段名 排序规则
     * @return
     */
    public List<MouseImgDo> getMouseImgDoByPublic(Map map);

    /**
     * 根据mid 获取图标
     * @param mid
     * @return
     */
    public MouseImgDo getMouseImgByMid(Long mid);

    /**
     * 根据mid 获取鼠标映射表
     * @param mid
     * @return
     */
    public List<MouseStyleDo> getMouseStyleDoByMid(Long mid);


}
