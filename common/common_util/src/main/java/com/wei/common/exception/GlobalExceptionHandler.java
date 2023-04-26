package com.wei.common.exception;


import com.wei.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//Controller层切面增强
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody//返回值作为请求的返回内容
    @ExceptionHandler(Exception.class)//指定处理的异常类型
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }
}
