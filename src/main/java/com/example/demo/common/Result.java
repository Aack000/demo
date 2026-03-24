package com.example.demo.common;

/**
 * 统一响应体类
 * 泛型类，用于封装接口返回数据
 * @param <T> 数据类型
 */
public class Result<T> {
    
    /**
     * 状态码
     * 200: 成功
     * 500: 服务器内部错误
     */
    private Integer code;
    
    /**
     * 提示信息
     */
    private String msg;
    
    /**
     * 核心数据
     */
    private T data;
    
    /**
     * 无参构造方法
     */
    public Result() {
    }
    
    /**
     * 全参构造方法
     * @param code 状态码
     * @param msg 提示信息
     * @param data 核心数据
     */
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    /**
     * 成功响应静态方法
     * @param data 返回数据
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }
    
    /**
     * 成功响应静态方法（带自定义消息）
     * @param msg 自定义消息
     * @param data 返回数据
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200, msg, data);
    }
    
    /**
     * 错误响应静态方法
     * @param msg 错误信息
     * @return Result对象
     */
    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }
    
    /**
     * 错误响应静态方法（带自定义状态码）
     * @param code 状态码
     * @param msg 错误信息
     * @return Result对象
     */
    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }
    
    /**
     * 获取状态码
     * @return 状态码
     */
    public Integer getCode() {
        return code;
    }
    
    /**
     * 设置状态码
     * @param code 状态码
     */
    public void setCode(Integer code) {
        this.code = code;
    }
    
    /**
     * 获取提示信息
     * @return 提示信息
     */
    public String getMsg() {
        return msg;
    }
    
    /**
     * 设置提示信息
     * @param msg 提示信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    /**
     * 获取核心数据
     * @return 核心数据
     */
    public T getData() {
        return data;
    }
    
    /**
     * 设置核心数据
     * @param data 核心数据
     */
    public void setData(T data) {
        this.data = data;
    }
    
    /**
     * 重写toString方法
     * @return 响应信息字符串
     */
    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}