package com.jdh.mapper;

import com.jdh.pojo.Permission;
import com.jdh.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 查询当前用户
     * @return
     */
    public User findByUserName(String username);

    /**
     * 通过名字查询用户权限
     * @param username
     * @return
     */
    public List<Permission> findPermissionByUsername(String username);

    /**
     * 修改密码
     * @param user
     */
    public void updatePassword(User user) ;
}
