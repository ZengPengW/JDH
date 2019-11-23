package com.jdh.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 图形验证码验证
 * @author zp
 *
 */
@Component
public class ImageCodeAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//判断当前请求是否为登录请求
		if (request.getRequestURI().contains("securityLogin")) {
			
			try {
				//校验验证码 imgCode
				String imgCode = (String) request.getParameter("imgCode");				
				
				String imgCodeSession=(String) request.getSession().getAttribute("imgCode");
				
				if(imgCode==null||imgCode.length()==0){
					throw new ImageCodeException("验证码未填写");
				}
				
				if(imgCodeSession==null||!imgCode.toLowerCase().equals(imgCodeSession.toLowerCase())){
					throw new ImageCodeException("验证码错误");
				}
			} catch (AuthenticationException e) {
				//交给authenticationFailureHandler
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
			
		
		}
		filterChain.doFilter(request, response);

	}
	
}
