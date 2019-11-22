package com.jdh.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;


@Configuration
public class ConfigBean {
	
	@Bean
	@LoadBalanced //负载均衡 默认轮询
	public RestTemplate geRestTemplate(){
		return new RestTemplate();
	}
	
	@Bean //存在显示声明就用声明的策略，此处是随机策略
	public IRule myRule(){
	//	return new RoundRobinRule(); //循环轮询
	//	return new RetryRule();//循环轮询，存在重试机制，如果获取到失败的就找下一个，重试阈值过后没有就返回null
		return new RandomRule();//随机
	}
}
