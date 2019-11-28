package com.jdh.pojo;

import java.io.Serializable;

/**
 * 背景信息
 */
public class BackgroundInfo implements Serializable {

    private String picPath;//背景地址
    private String sicPath;//特效地址

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getSicPath() {
        return sicPath;
    }

    public void setSicPath(String sicPath) {
        this.sicPath = sicPath;
    }
}
