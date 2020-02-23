package com.jdh.pojo;

import java.io.Serializable;

/*
CREATE TABLE `floater_style` (
  `uid` INT(11)  NOT NULL UNIQUE COMMENT '用户id',
  `fid` BIGINT(20) NOT NULL  COMMENT '漂浮物fid'
)COMMENT='漂浮物样式表';
 */
public class FloaterStyleDo implements Serializable {
    private Integer uid;
    private Long fid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }
}
