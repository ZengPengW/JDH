package com.jdh.pojo;

import java.io.Serializable;
import java.util.Date;

/*
CREATE TABLE `floater_img`(
`fid` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '漂浮物 id',
`path_one` VARCHAR(500) COMMENT '漂浮物地址1',
`path_tow` VARCHAR(500) COMMENT '漂浮物地址2',
`path_three` VARCHAR(500) COMMENT '漂浮物地址3',
`use_count` BIGINT(20) DEFAULT '1' COMMENT '使用次数',
`up_date` DATETIME DEFAULT NULL COMMENT '上传时间'
)COMMENT='漂浮物';
 */
public class FloaterImgDo implements Serializable {
    private Long fid;
    private String pathOne;
    private String pathTow;
    private String pathThree;
    private Long useCount;
    private Date upDate;
    private String view;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getPathOne() {
        return pathOne;
    }

    public void setPathOne(String pathOne) {
        this.pathOne = pathOne;
    }

    public String getPathTow() {
        return pathTow;
    }

    public void setPathTow(String pathTow) {
        this.pathTow = pathTow;
    }

    public String getPathThree() {
        return pathThree;
    }

    public void setPathThree(String pathThree) {
        this.pathThree = pathThree;
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
}
