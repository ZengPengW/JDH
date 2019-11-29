package com.jdh.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

public class BingBgImgBean {
    private   String imgPath;


    public void setImgPath(String imgPath) {
        System.out.println("设置图片值");
        this.imgPath = imgPath;
    }

    public String getImgPath() {
        System.out.println("获取图片值"+imgPath);
        return imgPath;
    }
    public  void updateImgPath()  {
        String bingImg = null;
        try {
            bingImg = BingBgIMG.getBingImg();
        } catch (Exception e) {
            System.out.println("获取bing图片失败 bean");
        }
        setImgPath(bingImg);
    }
}

