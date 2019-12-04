package com.jdh.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

public class MyThumbnail {

    /**
     *
     * @param fromPic 图片来自何处
     * @param toPic 生成的缩略图地址
     * @param width 缩略图宽
     * @param height 缩略图高
     * @return 缩略图地址
     *
     */
    public static boolean getThumbnail(String fromPic,String toPic,Integer width,Integer height)  {
       try {
           File fromPicfile=new File(fromPic);
           File toPicfile=new File(toPic);
           Thumbnails.of(fromPicfile).size(width,height)
                   .keepAspectRatio(false)//不保持比例
                   .toFile(toPicfile);
           return true;//成功
       }catch (Exception e){
           e.printStackTrace();
           return false;//失败
       }



    }
}
