package com.sea.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 引用晓风轻的ResultBean
 * @author 肖文杰
 * @param <T>
 */
@Data
public class ResultVO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int NO_LOGIN = -1;

	public static final int SUCCESS = 0;

	public static final int FAIL = 1;

	public static final int NO_PERMISSION = 2;

	private String message = "success";

	private int code = SUCCESS;

	private T data;

	private long timestamp = System.currentTimeMillis();


	public ResultVO() {
		super();
	}

	public ResultVO(T data) {
		super();
		this.data = data;
	}

	public ResultVO(Throwable e) {
		super();
		this.message = e.toString();
		this.code = FAIL;
	}


}
