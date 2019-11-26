package com.jdh.service.impl;

import java.util.List;

import com.jdh.mapper.UserMapper;
import com.jdh.pojo.Permission;
import com.jdh.pojo.User;
import com.jdh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public User findByUserName(String username) {
		return userMapper.findByUserName(username);
	}

	@Override
	public List<Permission> findPermissionByUsername(String username) {
		return userMapper.findPermissionByUsername(username);
	}

	@Override
    @Transactional(isolation=Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updatePassword(User user) {
		String password=user.getPassword();
		//哈希算法+加变量 加密密码
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));
		
		userMapper.updatePassword(user);
	}

}
