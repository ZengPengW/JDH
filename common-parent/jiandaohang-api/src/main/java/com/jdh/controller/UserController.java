package com.jdh.controller;

import com.jdh.pojo.Permission;
import com.jdh.pojo.User;
import com.jdh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    /**
     * 查询当前用户
     * @return
     */
    @GetMapping("/user/{username}")
    public User findByUserName(@PathVariable(name = "username",required = true ) String username){
        User user = userService.findByUserName(username);

        if (user!=null)return  user;
        else {
            user =new User();
            user.setUsername("null");
            return  user;
        }
    }

    /**
     * 通过名字查询用户权限
     * @param username
     * @return
     */
    @GetMapping("/permission/{username}")
    public List<Permission> findPermissionByUsername(@PathVariable(name = "username",required = true)String username){

       return userService.findPermissionByUsername(username);
    }

    /**
     * 修改密码
     * @param user
     */
    @PostMapping("/change/password")
    public Map updatePassword(User user) {
        Map map= new HashMap();
        try {
            userService.updatePassword(user);
            map.put("code",1);
            map.put("message","修改成功");
        }catch (Exception ignored){
            map.put("code",2);
            map.put("message","修改失败");
        }finally {
            return map;
        }

    }
}
