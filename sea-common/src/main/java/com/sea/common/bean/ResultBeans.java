package com.sea.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 *   接口返回数据格式
 * @author scott
 * @email jeecgos@163.com
 * @date  2019年1月19日
 */
@Data
public class ResultBeans<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	private boolean success;

	/**
	 * 返回处理消息
	 */
	private String message;

	/**
	 * 返回代码
	 */
	private Integer code;
	
	/**
	 * 返回数据对象 data
	 */
	private T result;

	/**
	 * 时间戳
	 */
	private long timestamp = System.currentTimeMillis();

	public void error500(String message) {
		this.message = message;
		this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
		this.success = false;
	}

	public void success(String message) {
		this.message = message;
		this.code = CommonConstant.SC_OK_200;
		this.success = true;
	}
	
	public static ResultBeans<Object> error(String msg) {
		return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
	}
	
	public static ResultBeans<Object> error(int code, String msg) {
		ResultBeans<Object> r = new ResultBeans<Object>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}
	
	public static ResultBeans<Object> ok(String msg) {
		ResultBeans<Object> r = new ResultBeans<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage(msg);
		return r;
	}
	
	public static ResultBeans<Object> ok(Object obj) {
		ResultBeans<Object> r = new ResultBeans<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setResult(obj);
		return r;
	}
}
