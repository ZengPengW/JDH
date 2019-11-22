package com.jdh.service;

import com.jdh.pojo.Permission;
import com.jdh.pojo.User;

import java.util.List;



public interface UserService {
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
