package com.jdh.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 鼠标图标表
 *
 * -- create table `mouse_img`(
 * -- `uid` int not null comment '上传者uid',
 * -- `mid` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '鼠标id mid',
 * -- `mouse_type` tinyint not null comment '鼠标类型 0=指针 1=手指 2=都可以',
 * -- `mouse_url` varchar(500) comment '图标地址',
 * -- `md5` varchar(500) not null comment '图片md5',
 * -- `use_count` bigint(20) DEFAULT '1',
 * -- `up_date` datetime DEFAULT NULL COMMENT '上传时间',
 * -- `share` boolean DEFAULT '0' COMMENT '是否共享'
 * -- )comment='鼠标图标';
 *
 *
 *
 */
public class MouseImgDo implements Serializable {
    private Integer uid;//上传者id
    private Long mid;   //图标id
    private Integer mouseType;//鼠标类型 0=指针 1=手指 2=都可以
    private String mouseUrl;//图标地址
    private String md5; //md5值
    private Long useCount;//使用次数
    private Date upDate; //上传时间
    private Boolean share;//是否共享

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Integer getMouseType() {
        return mouseType;
    }

    public void setMouseType(Integer mouseType) {
        this.mouseType = mouseType;
    }

    public String getMouseUrl() {
        return mouseUrl;
    }

    public void setMouseUrl(String mouseUrl) {
        this.mouseUrl = mouseUrl;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getUseCount() {
        return useCount;
    }

    public void setUseCount(Long useCount) {
        this.useCount = useCount;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    public Boolean getShare() {
        return share;
    }

    public void setShare(Boolean share) {
        this.share = share;
    }
}
