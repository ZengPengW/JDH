package com.jdh.pojo;

import java.io.Serializable;
import java.util.Date;

/*
CREATE TABLE `player_img`(
`uid` INT NOT NULL COMMENT '上传者uid',
`pid` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '播放器id',
`player_url` VARCHAR(500) COMMENT '图标地址',
`md5` VARCHAR(500) NOT NULL COMMENT '图片md5',
`use_count` BIGINT(20) DEFAULT '1',
`up_date` DATETIME DEFAULT NULL COMMENT '上传时间',
`share` BOOLEAN DEFAULT '0' COMMENT '是否共享'
)COMMENT='播放器图';
 */
public class PlayerImgDo implements Serializable {
    private Integer uid; //上传者uid
    private Long pid;//播放器id
    private  String playerUrl;//图标地址
    private  String md5;
    private Long useCount;
    private Date upDate;
    private Boolean share;

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

    public String getPlayerUrl() {
        return playerUrl;
    }

    public void setPlayerUrl(String playerUrl) {
        this.playerUrl = playerUrl;
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
