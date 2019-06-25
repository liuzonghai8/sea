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
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer NO_LOGIN = -1;//没有权限
	public static final Integer SUCCESS = 0;
	public static final Integer FAIL = 1;
	public static final Integer NO_PERMISSION = 2;//没有权限

	/**
	 * 返回处理消息
	 */
	private String message = "success";

	/**
	 * 返回代码
	 */
	private Integer code = SUCCESS;
	
	/**
	 * 返回数据对象 data
	 */
	private T result;

	/**
	 * 时间戳
	 */
	private long timestamp = System.currentTimeMillis();

	public Result() {
		super();
	}

	public Result(T data) {
		super();
		this.result = data;
	}

	public Result(Throwable e) {
		super();
		this.message = e.toString();
		this.code = FAIL;
	}

//	public static Result<Object> error(int code ,String message){
//		return null;
//	}

}
