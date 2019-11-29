package com.jdh.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler{

	private ObjectMapper objectMapper=new ObjectMapper();

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		Map<Object, Object> map=new HashMap<Object, Object>();
   //    System.out.println("失败登录");
		//验证码
		String message = exception.getMessage();
		map.put("errorMsg", message);
		if(message!=null&&message.contains("验证码")){
			map.put("status",3);
		}else {
			map.put("status", 2); //1成功 2失败 3验证码错误
		}
		
		
		
		String json = objectMapper.writeValueAsString(map);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(json);
		
	}

}
