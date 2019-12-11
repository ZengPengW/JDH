package com.jdh.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 背景特效 pojo
 *
 * sid bigint(20) NOT NULL背景特效id
 * special varchar(500) NULL背景特效地址
 * author_id int(11) NULL上传者id
 * up_date  datetime NULL上传时间
 * use_count  bigint(20) NULL使用次数
 * thumbnail  varchar(500) NULL缩略图
 * name varchar(200) NULL 特效名称
 *transparent tinyint(1) NULL是否透明
 */
public class BackgroundSpeDo implements Serializable {
    private Long sid; //背景特效id
    private String special; //背景特效地址
    private int author_id; //上传者id
    private Date up_date; //上传时间
    private Long use_count; //使用次数
    private String thumbnail; //缩略图
    private String name; //特效名称
    private boolean transparent;//是否透明


    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    public Long getUse_count() {
        return use_count;
    }

    public void setUse_count(Long use_count) {
        this.use_count = use_count;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
