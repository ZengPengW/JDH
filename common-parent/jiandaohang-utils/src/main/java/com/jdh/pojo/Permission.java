package com.jdh.pojo;

import java.io.Serializable;



public class Permission implements Serializable{
	private Integer id;//int(11) NOT NULL权限id
	private String permName;//varchar(50) NULL权限名
	private String permTag;//varchar(50) NULL权限标识符

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermTag() {
        return permTag;
    }

    public void setPermTag(String permTag) {
        this.permTag = permTag;
    }
}
