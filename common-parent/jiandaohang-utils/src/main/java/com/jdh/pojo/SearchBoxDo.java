package com.jdh.pojo;

import java.io.Serializable;

/**
 * 搜索框表
 * CREATE TABLE search_box(
 * uid INT COMMENT '用户id',
 * sid BIGINT  NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '搜索框sid',
 * border_color VARCHAR(200) NOT NULL DEFAULT '#000000' COMMENT '边框初始颜色',
 * border_change_color BOOLEAN NOT NULL DEFAULT TRUE COMMENT '边框是否自动变色',
 * text_color VARCHAR(200) NOT NULL DEFAULT '#000000' COMMENT '字体初始颜色',
 * text_change_color BOOLEAN NOT NULL DEFAULT TRUE COMMENT '字体是否自动变色'
 * );
 */
public class SearchBoxDo implements Serializable {
    private Integer uid;//用户id
    private Long sid;//搜索框sid
    private String borderColor;//边框初始颜色
    private Boolean borderChangeColor;//边框是否自动变色
    private String textColor;//字体初始颜色
    private Boolean textChangeColor;//字体是否自动变色

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public Boolean getBorderChangeColor() {
        return borderChangeColor;
    }

    public void setBorderChangeColor(Boolean borderChangeColor) {
        this.borderChangeColor = borderChangeColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Boolean getTextChangeColor() {
        return textChangeColor;
    }

    public void setTextChangeColor(Boolean textChangeColor) {
        this.textChangeColor = textChangeColor;
    }
}
