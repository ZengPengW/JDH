package com.jdh.pojo;

import java.io.Serializable;

/*
背景表
 */
public class Background implements Serializable {
    private Integer uid;//用户id
    private Long pid;//背景图id
    private Long sid;//背景特效id

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }
}
