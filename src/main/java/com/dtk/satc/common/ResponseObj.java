package com.dtk.satc.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResponseObj<T> {

    private static final String SUCCESS_CODE = "200";
    private static final String SUCCESS_MESSAGE = "success";
    private static final String FAIL_CODE = "FAIL";
    private static final String FAIL_MESSAGE = "error";
    private static final String PARAM_ERROR_CODE = "400";
    private static final String PARAM_ERROR_MESSAGE = "参数错误";
    private static final String ERROR_CODE = "500";
    private static final String ERROR_MESSAGE = "请稍候再试";
    @ApiModelProperty(value = "返回CODE", required = true)
    private String code;

    @ApiModelProperty(value = "返回信息", required = true)
    private String msg;

    @ApiModelProperty(value = "返回对象数据", required = true)
    private T data;

    public ResponseObj(){}

    public ResponseObj(String code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseObj<T> ok() {
        return new ResponseObj(SUCCESS_CODE, "success", null);
    }

    public static <T> ResponseObj<T> ok(T data) {
        return new ResponseObj(SUCCESS_CODE, "success", data);
    }

    public static <T> ResponseObj<T> ok(String code, String message) {
        return new ResponseObj(code, message, null);
    }

    public static <T> ResponseObj<T> ok(String code, String message, T data) {
        return new ResponseObj(code, message, data);
    }

    public static <T> ResponseObj<T> fail() {
        return new ResponseObj(ERROR_CODE, "error", null);
    }

    public static <T> ResponseObj<T> fail(String message) {
        return new ResponseObj(ERROR_CODE, message, null);
    }

    public static <T> ResponseObj<T> fail(String code, String message) {
        return new ResponseObj(code, message, null);
    }

    public static <T> ResponseObj<T> fail(String code, String message, T t) {
        return new ResponseObj(code, message, t);
    }

    public static <T> ResponseObj<T> paramError() {
        return new ResponseObj(PARAM_ERROR_CODE, "param_error", null);
    }

    public static <T> ResponseObj<T> paramError(String message) {
        return new ResponseObj(PARAM_ERROR_CODE, message, null);
    }

    public static <T> ResponseObj<T> paramError(String code, String message) {
        return new ResponseObj(code, message, null);
    }

    public static <T> ResponseObj<T> paramError(String code, String message, T t) {
        return new ResponseObj(code, message, t);
    }

    public static <T> ResponseObj<T> error() {
        return new ResponseObj(ERROR_CODE, "error", null);
    }

    public static <T> ResponseObj<T> error(String code, String message) {
        return new ResponseObj(code, message, null);
    }

    public static <T> ResponseObj<T> error(String code, String message, T t) {
        return new ResponseObj(code, message, t);
    }
}
