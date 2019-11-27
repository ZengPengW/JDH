package com.jdh.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 验证成功后操作
 * @author zp
 *
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private ObjectMapper objectMapper=new ObjectMapper();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	    //登录成功修改登录时间
     //   System.out.println("成功登录");
		Map<Object, Object> map=new HashMap<Object, Object>();
		map.put("status", 1); //1成功 2失败
		request.getSession().removeAttribute("imgCode"); //登录成功后删除验证码
		//System.out.println("成功了");
		String json = objectMapper.writeValueAsString(map);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(json);
		
		
	}

}
