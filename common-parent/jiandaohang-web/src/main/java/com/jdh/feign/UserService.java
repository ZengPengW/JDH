package com.jdh.feign;

import com.jdh.feign.fallbackFactory.UserServiceFallbackFactory;
import com.jdh.pojo.Permission;
import com.jdh.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FeignClient(name = "JIANDAOHANG-API",fallbackFactory= UserServiceFallbackFactory.class)
public interface UserService {


    /**
     * 查询当前用户
     * @return
     */
    @GetMapping("/user/{username}")
    public User findByUserName(@PathVariable(name = "username",required = true ) String username);

    /**
     * 通过名字查询用户权限
     * @param username
     * @return
     */
    @GetMapping("/permission/{username}")
    public List<Permission> findPermissionByUsername(@PathVariable(name = "username",required = true)String username);

    /**
     * 修改密码
     * @param user
     */
    @PostMapping("/change/password")
    public Map updatePassword(User user) ;
}
