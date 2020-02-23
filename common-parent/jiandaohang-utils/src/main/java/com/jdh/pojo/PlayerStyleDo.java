package com.jdh.pojo;

import java.io.Serializable;

/*
CREATE TABLE `player_style` (
  `uid` INT(11)  NOT NULL UNIQUE COMMENT '用户id',
  `pid` BIGINT(20) NOT NULL  COMMENT '播放器id'
)COMMENT='播放器映射样式表';
 */
public class PlayerStyleDo implements Serializable {
    private Integer uid;
    private Long pid;

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
}
