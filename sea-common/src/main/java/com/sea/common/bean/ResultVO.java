package com.sea.common.bean;

import java.io.Serializable;

public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Integer NO_LOGIN = -1;
    public static final Integer SUCCESS = 0;
    public static final Integer FAIL = 1;
    public static final Integer NO_PERMISSION = 2;

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

    public ResultVO() {
        super();
    }

    public ResultVO(T data) {
        super();
        this.result = data;
    }

    public ResultVO(Throwable e) {
        super();
        this.message = e.toString();
        this.code = FAIL;
    }
}
