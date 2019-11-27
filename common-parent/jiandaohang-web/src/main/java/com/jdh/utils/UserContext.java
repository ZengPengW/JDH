package com.jdh.utils;

import com.jdh.pojo.MyUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class UserContext {



    public static MyUser  getCurrUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            MyUser userDetails = (MyUser) principal;
           return userDetails;
        }

        return  null;
    }
}
