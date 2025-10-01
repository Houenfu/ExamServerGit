package com.itszt.ExamServer.exceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itszt.ExamServer.entity.HttpResult;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Component
public class CommonsExceptionHandler {

    @SneakyThrows
    @ExceptionHandler(Exception.class)
    public String dealException(Exception e){

        e.printStackTrace();

        return new ObjectMapper().writeValueAsString(new HttpResult(500, "请求繁忙！", null));
    }

    @SneakyThrows
    @ExceptionHandler(IllegalArgumentException.class)
    public String dealException(IllegalArgumentException e){

        e.printStackTrace();

        return new ObjectMapper().writeValueAsString(new HttpResult(500, e.getMessage(), null));
    }
}
