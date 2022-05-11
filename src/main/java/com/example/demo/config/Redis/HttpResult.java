package com.example.demo.config.Redis;

/**
 * <pre>
 *  描述:一个http响应结果通用类
 * </pre>
 *
 * @author KyleWong(wangfan)
 * @version 1.0.0
 * @date 2019/12/3 9:28
 */
public class HttpResult {
    private int code = 200;
    //private static final int FAIL_CODE = 500;
    private String msg;
    private Object data;

    /**
     * 请求成功时的响应结果
     * @return
     */
    public static HttpResult success(){
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(httpResult.code);
        httpResult.setMsg("SUCCESS");
        return httpResult;
    }

    /**
     * 请求成功时的响应结果
     * @param msg 自定义响应内容
     * @return
     */
    public static HttpResult success(String msg){
        HttpResult httpResult = new HttpResult();
        httpResult.setMsg(msg);
        httpResult.setCode(httpResult.code);
        return httpResult;
    }

    /**
     * 请求成功时的响应结果
     * @param data 要响应的数据
     * @return
     */
    public static HttpResult success(Object data){
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(httpResult.code);
        httpResult.setMsg("SUCCESS");
        httpResult.setData(data);
        return httpResult;
    }

    /**
     * 请求成功时的响应结果
     * @param msg 自定义响应的内容
     * @param data 自定义响应数据
     * @return
     */
    public static HttpResult success(String msg, Object data){
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(httpResult.code);
        httpResult.setMsg(msg);
        httpResult.setData(data);
        return httpResult;
    }

    /**
     * 请求失败时的响应内容
     * @return
     */
    public static HttpResult failed(){
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(500);
        httpResult.setMsg("soething is wrong");
        return httpResult;
    }

    /**
     * 请求失败时的相应内容
     * @param msg 自定义相应内容
     * @return
     */
    public static HttpResult failed(String msg){
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(500);
        httpResult.setMsg(msg);
        return httpResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}