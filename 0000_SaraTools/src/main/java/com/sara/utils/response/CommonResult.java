package com.sara.utils.response;

import java.io.Serializable;

public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = -3068537877457455674L;

    private static final String SUCCESS_CODE = "0000";
    private static final String SUCCESS_MSG = "成功";

    private static final String FAIL_CODE = "0001";
    private static final String FAIL_MSG = "系统繁忙！";

    /**
     * 返回码:0000-成功，
     */
    private String code;

    /**
     * 返回信息
     */
    private String smg;

    /**
     * 返回数据
     */
    private T data;

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.getCode());
    }

    public CommonResult<T> success() {
        return this.setCode(SUCCESS_CODE).setSmg(SUCCESS_MSG);
    }

    public CommonResult<T> success(String msg) {
        return this.setCode(SUCCESS_CODE).setSmg(msg);
    }

    public CommonResult<T> success(String code, String msg) {
        return this.setCode(code).setSmg(msg);
    }

    public CommonResult<T> success(String code, String msg, T t) {
        return this.setCode(code).setSmg(msg).setData(t);
    }

    public CommonResult<T> success(T t) {
        return this.setCode(SUCCESS_CODE).setSmg(SUCCESS_MSG).setData(t);
    }

    public CommonResult<T> fail() {
        return this.setCode(FAIL_CODE).setSmg(FAIL_MSG);
    }

    public CommonResult<T> fail(String msg) {
        return this.setCode(FAIL_CODE).setSmg(msg);
    }

    public CommonResult<T> fail(String code, String msg) {
        return this.setCode(code).setSmg(msg);
    }

    public CommonResult<T> fail(String code, String msg, T t) {
        return this.setCode(code).setSmg(msg).setData(t);
    }

    public CommonResult<T> fail(T t) {
        return this.setCode(FAIL_CODE).setSmg(FAIL_MSG).setData(t);
    }

    public String getCode() {
        return code;
    }

    public CommonResult setCode(String code) {
        this.code = code;
        return this;
    }

    public String getSmg() {
        return smg;
    }

    public CommonResult setSmg(String smg) {
        this.smg = smg;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResult setData(T data) {
        this.data = data;
        return this;
    }
}
