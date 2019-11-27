package com.jdh.service;

import com.jdh.pojo.BackgroundImgDo;

public interface BackgroundService {
    /**
     * 新增背景图片
     * @param pic 图片地址
     * @param authorId 上传者id 默认0
     */
    public void addBackgroundImg(BackgroundImgDo backgroundImgDo);
}
