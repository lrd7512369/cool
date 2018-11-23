package com.lrd.cool.api.response;

/**
 * api返回
 *
 * @author lierdong
 */
public class ApiResult {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应实体类
     */
    private Object value;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
