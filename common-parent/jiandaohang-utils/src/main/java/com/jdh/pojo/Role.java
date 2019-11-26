package com.jdh.pojo;

import java.io.Serializable;


/*
角色表
 */

public class Role implements Serializable {
	private Integer id;//int(11) NOT NULL角色id
	private String roleName;//varchar(50) NOT NULL角色名
	private String roleDesc;//varchar(50) NOT NULL角色说明

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
