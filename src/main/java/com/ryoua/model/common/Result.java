package com.ryoua.model.common;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
public class Result<T> {

    //操作代码
    int code;

    //提示信息
    String msg;

    //结果数据
    T data;

    long count = 1;

    public Result(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public Result(int code, String message, T data, long count) {
        this.code = code;
        this.msg = message;
        this.data = data;
        this.count = count;
    }

    public Result(ResultCode resultCode){
        this.code = resultCode.code();
        this.msg = resultCode.message();
    }

    public Result(ResultCode resultCode, T data){
        this.code = resultCode.code();
        this.msg = resultCode.message();
        this.data = data;
    }

    public Result(String message){
        this.msg = message;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    public static <T> Result SUCCESS(T data){
        return new Result(ResultCode.SUCCESS, data);
    }

    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }

    public static Result FAIL(String message){
        return new Result(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

