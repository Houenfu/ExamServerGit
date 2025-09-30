package com.itszt.ExamServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itszt.ExamServer.entity.HttpResult;
import com.itszt.ExamServer.entity.User;
import com.itszt.ExamServer.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    // task1: login, 增，删，改
    // task2：查询，出题，算分, exceptionHandler
    @SneakyThrows
    @GetMapping("login")
    public String login(@RequestParam String username, @RequestParam String password){

        System.out.println("username = " + username + ", password = " + password);

        userService.validate(username, password);

        User user=userService.getUser(username, password);
        System.out.println("user = " + user);

        // msg, status;
        if(user!=null){

            // todo 返回正确结果！
            return new ObjectMapper().writeValueAsString(new HttpResult(200, "登录成功！"));
        }else{

            // 返回错误结果！
            return new ObjectMapper().writeValueAsString(new HttpResult(400, "登录失败！"));
        }
    }





}
