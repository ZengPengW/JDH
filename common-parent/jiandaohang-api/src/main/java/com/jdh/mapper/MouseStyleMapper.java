package com.jdh.mapper;

import com.jdh.pojo.MouseImgDo;
import com.jdh.pojo.MouseStyleDo;
import org.apache.ibatis.annotations.Mapper;

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
    public MouseImgDo getMouseImgDoByMd5(String md5);


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
}
