package com.jdh.pojo;

import java.io.Serializable;

/*

Create Table

CREATE TABLE `player_music` (
  `mid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'music id',
  `music` varchar(500) DEFAULT NULL COMMENT '地址',
  `uid` int(11) NOT NULL COMMENT '使用者uid',
  `author` varchar(500) NOT NULL COMMENT '歌手',
  `music_name` varchar(500) NOT NULL COMMENT '歌名',
  PRIMARY KEY (`mid`),
  UNIQUE KEY `uid` (`uid`),
  CONSTRAINT `player_music_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='背景音乐'

 */
public class PlayerMusicDo implements Serializable {
    private Long mid;
    private String music;//链接地址
    private Integer uid;
    private String author; //歌手
    private String musicName; //歌曲名

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }
}
