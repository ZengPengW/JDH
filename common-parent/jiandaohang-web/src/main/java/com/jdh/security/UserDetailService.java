package com.jdh.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jdh.feign.UserService;
import com.jdh.pojo.MyUser;
import com.jdh.pojo.Permission;
import com.jdh.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义验证
 * @author zp
 *
 */
@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
	private UserService userService;
//    private Integer id; //int(10) NOT NULL用户id
//    private String username; //varchar(50) NOT NULL用户名
//    private String email;//varchar(50) NULL邮箱
//    private String password;//varchar(50) NULL密码
//    private Date createDate ;//date NULL创建日期
//    private Date lastLoginTime;//date NULL最后登录时间
//    private boolean enabled ;//int(5) NULL是否可用
//    private boolean accountNonExpired; //int(5) NULL是否过期
//    private boolean accountNonLocked; //int(5) NULL是否锁定
//    private boolean credentialsNonExpired;//int(5) NULL证书是否过期
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("调用登录"+username);
		//查询用户
		User user =  userService.findByUserName(username);
		MyUser myUser=null;
		if(user!=null){
     //       System.out.println("调用登录999");
		    myUser=new MyUser();
		    myUser.setUsername(user.getUsername());
		    myUser.setPassword(user.getPassword());
		    myUser.setId(user.getId());
		    myUser.setEmail(user.getEmail());
		    myUser.setCreateDate(user.getCreateDate());
		    myUser.setLastLoginTime(user.getLastLoginTime());
		    myUser.setEnabled(user.isEnabled());
		    myUser.setAccountNonExpired(user.isAccountNonExpired());
		    myUser.setAccountNonLocked(user.isAccountNonLocked());
		    myUser.setCredentialsNonExpired(user.isCredentialsNonExpired());


		//查询权限
		List<Permission> permissions = userService.findPermissionByUsername(myUser.getUsername());
		
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();

		for (Permission permission : permissions) {
			authorities.add(new SimpleGrantedAuthority(permission.getPermTag()));
		}
            myUser.setAuthorities(authorities);
		}else {
            System.out.println("没查询到： "+username);
        }
		
		return myUser;
	}

}
