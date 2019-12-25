package com.jdh.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 背景图片 pojo
 *
 *

 pidbigint(20) NOT NULL背景图片id
 picvarchar(500) NULL背景图片地址
 author_idint(11) NULL上传者id
 expiretinyint(1) NULL是否到期
 ispublictinyint(1) NULL是否共享
 md5varchar(500) NULL文件md5值
 thumbnailvarchar(500) NULL缩略图
 up_datedatetime NULL上传时间
 use_countbigint(20) NULL

 */
public class BackgroundImgDo implements Serializable {
    private Long pid; //图片id
    private String pic; //图片地址
    private Integer authorId; //上传者id
    private Boolean expire; //是否过期
    private Boolean ispublic;//是否公开
    private String md5;
    private String thumbnail;//缩略图
    private Date upDate; //上传时间
    private Long useCount; //使用次数

    //可选参数
    private String authorName;//上传者名称

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }


    public Boolean getExpire() {
        return expire;
    }

    public void setExpire(Boolean expire) {
        this.expire = expire;
    }

    public Boolean getIspublic() {
        return ispublic;
    }

    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
