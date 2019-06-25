package com.sea.common.bean;

import com.sea.common.utils.SeaCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * 引用晓风轻的ResultBean
 * @author 肖文杰
 * @param <T>
 */
@Data
public class ResultVO<T> implements Serializable {

	private static final long serialVersionUID = 1L;


	private String message;

	private Integer code;

	private T result;

	private long timestamp = System.currentTimeMillis();


	public ResultVO() {
		super();
	}

	public ResultVO(T result) {
		super();
		this.code = SeaCode.SUCCESS;
		this.message = "success";
		this.result = result;
	}
	public ResultVO(T result, @Nullable String message) {
		super();
		this.code = SeaCode.SUCCESS;
		this.message = message;
		this.result = result;
	}

	public ResultVO(Integer code , String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public ResultVO(Throwable e) {
		super();
		this.message = e.toString();
		this.code = SeaCode.FAIL;
	}


}
