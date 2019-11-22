package com.jdh.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyUser extends User implements Serializable, UserDetails {
    //用户拥有的所有权限
    private List<GrantedAuthority> authorities =new ArrayList<GrantedAuthority>();

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
