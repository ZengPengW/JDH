package com.jdh.pojo;

import java.io.Serializable;

/**
 * 用户鼠标映射表
 *
 * -- CREATE TABLE `mouse_style` (
 * --   `uid` int(11)  not null unique COMMENT '用户id',
 * --   `pmid` bigint(20) NOT NULL  COMMENT '鼠标指针id',
 * --   `hmid` bigint not null  comment '鼠标手指id '
 * -- )COMMENT='鼠标样式表';
 */
public class MouseStyleDo implements Serializable {

    private Integer uid;//用户id
    private Long pmid;//鼠标指针id
    private Long hmid;//鼠标手指id

    //可选参数
    private String purl;//指针地址
    private String hurl;//手指地址

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getPmid() {
        return pmid;
    }

    public void setPmid(Long pmid) {
        this.pmid = pmid;
    }

    public Long getHmid() {
        return hmid;
    }

    public void setHmid(Long hmid) {
        this.hmid = hmid;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getHurl() {
        return hurl;
    }

    public void setHurl(String hurl) {
        this.hurl = hurl;
    }
}
