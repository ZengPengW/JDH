package com.jdh.mapper;

import com.jdh.pojo.BackgroundImgDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BackgroundMapper {

    /**
     *
     * @param pic 图片地址
     * @param authorId 上传者id 默认0
     */
    public void addBackgroundImg(BackgroundImgDo backgroundImgDo);

    /**
     * 设置当前用户背景图片 背景图片启用
     */


    /**
     * 更新当前用户背景图片
     */
}
