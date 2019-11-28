package com.jdh.utils;

/**
 * 返回响应的实体类
 * @author zp
 *
 */
public class HttpResult {
	private Integer code;//状态码
	private String body;//响应体
	
	public HttpResult() {}
	
	public HttpResult(Integer code, String body) {
		super();
		this.code = code;
		this.body = body;
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
	
}