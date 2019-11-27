package com.jdh.security;

import javax.sql.DataSource;

import com.jdh.security.filter.StaticFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity //启动Security过滤器链
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private StaticFileFilter staticFileFilter;

	@Autowired
	private PersistentTokenRepository tokenRepository;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	

	@Autowired
	private MyAuthenticationFailHandler myAuthenticationFailHandler;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ImageCodeAuthenticationFilter imageCodeAuthenticationFilter;
	//<security:authentication-manager>
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("eric").password("123456").authorities("ROLE_ADD_PRODUCT","ROLE_UPDATE_PRODUCT");
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
	}
	
	
	//<security:http>
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();//使得frame可以成功加载
		http.authorizeRequests()
		.antMatchers("/js/**","/css/**","/material/**","/img/**").permitAll()//静态资源放行
		.antMatchers("/imgCode","/login","/index","/").permitAll()
//		.antMatchers("/product/add").hasAuthority("ROLE_ADD")
//		.antMatchers("/product/update").hasAuthority("ROLE_UPDATE")
//		.antMatchers("/product/delete").hasAuthority("ROLE_DELETE")
//		.antMatchers("/product/list").hasAuthority("ROLE_LIST")
		//.antMatchers("/**").fullyAuthenticated()//拦截所有包括记住我自动登录的用户
        .antMatchers("/**").authenticated()//拦截所以不包括记住我用户
		.and().formLogin().loginPage("/login").loginProcessingUrl("/securityLogin")//表单模式
		.successHandler(myAuthenticationSuccessHandler)//成功后
		.failureHandler(myAuthenticationFailHandler) //失败后
		//.defaultSuccessUrl("/index")
		.and().rememberMe().tokenRepository(tokenRepository).tokenValiditySeconds(31536000)//记住我
		.and().csrf().disable()//关闭esrt
        .logout().logoutSuccessUrl("/index").and()
		.addFilterBefore(imageCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)//验证码过滤器
        .addFilterBefore(staticFileFilter,ImageCodeAuthenticationFilter.class);
		
	}


	//记住我数据源
	@Bean
	public PersistentTokenRepository tokenRepository(DataSource dataSource){
		JdbcTokenRepositoryImpl tokenRepositoryImpl=new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		//tokenRepositoryImpl.setCreateTableOnStartup(true); // 第一次启动时可使用此功能自动创建表，第二次要关闭，否则表已存在会启动报错
		return tokenRepositoryImpl;
	}
	
	//密码加密
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
}
