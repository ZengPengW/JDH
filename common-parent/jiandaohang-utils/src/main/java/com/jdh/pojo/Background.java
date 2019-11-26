package com.jdh.pojo;

import java.io.Serializable;

/*
背景表
 */
public class Background implements Serializable {
    private Integer uid;//用户id
    private String pic;//背景图地址
    private String special;//背景特效地址

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}
