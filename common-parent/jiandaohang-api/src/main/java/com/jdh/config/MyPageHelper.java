package com.jdh.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyPageHelper {

	@Bean
	public PageHelper pageHelper(){
		
		PageHelper pageHelper=new PageHelper();
		Properties properties=new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");//分页合理化 大于页码 就返回最后一页
		pageHelper.setProperties(properties);
		return pageHelper;
		
	}
}