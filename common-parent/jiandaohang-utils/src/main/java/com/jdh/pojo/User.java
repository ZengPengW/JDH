package com.jdh.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
用户表
 */

public class User implements Serializable{
	private Integer id; //int(10) NOT NULL用户id
	private String username; //varchar(50) NOT NULL用户名
	private String email;//varchar(50) NULL邮箱
	private String password;//varchar(50) NULL密码
	private Date createDate ;//date NULL创建日期
	private Date lastLoginTime;//date NULL最后登录时间
	private boolean enabled ;//int(5) NULL是否可用
	private boolean accountNonExpired; //int(5) NULL是否过期
	private boolean accountNonLocked; //int(5) NULL是否锁定
	private boolean credentialsNonExpired;//int(5) NULL证书是否过期
	
	
	//用户拥有的所有权限
	//private  List<GrantedAuthority>  authorities =new ArrayList<GrantedAuthority>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
}
