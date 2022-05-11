package com.example.demo.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * 接口统一返回包装类
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;



    public static Result sucess(){
        return new Result(Constants.CODE_200 , "" ,null);
    }
    public static Result success(Object data)
    {
        return new Result(Constants.CODE_200,"登陆成功",data);
    }
    public static Result error(String code , String msg ,String data){
        return new Result(code,msg,data);
    }
    public static Result error(String 数据加密异常, JsonProcessingException e){
        return new Result(Constants.CODE_500,"系统错误",null);
    }

    public static Result success(String code, String msg, String data) {
        return new Result(code,msg,data);
    }
}

//    public static Result success() {
//        return new Result(Constants.CODE_200, "", null);
//    }
//
//    public static Result success(Object data) {
//        return new Result(Constants.CODE_200, "登陆成功",data);
//    }
//
//    public static Result error() {
//        return new Result();
//    }
//
//    public static Result error(String 数据加密异常, JsonProcessingException e) {
//        return new Result(Constants.CODE_500, "系统错误", null);
//    }
//}
