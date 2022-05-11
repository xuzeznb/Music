package com.example.demo.exception;
/**
* 异常处理器
*@param se 业务处理器
* @return Return
* */
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
     public Result handle(ServiceException se){
        return Result.error(Constants.CODE_400,"",null);
    }
}