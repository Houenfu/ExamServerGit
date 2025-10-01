package com.itszt.ExamServer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    // 需要添加cookie!!!验证身份！
    public static HashMap<String, String> uuidSet=new HashMap<>();

    @Autowired
    private UserService userService;

    // task1: login, 增，删，改
    // task2：查询，出题，算分, exceptionHandler
    @SneakyThrows
    @GetMapping("login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response, HttpServletRequest request) throws JsonProcessingException {

        System.out.println("username = " + username + ", password = " + password);

        userService.validate(username, password);

        User user=userService.getUser(username, password);
        System.out.println("user = " + user);

        // msg, status;
        if(user!=null){

            // todo 返回正确结果！
            String uuid = UUID.randomUUID().toString();
            System.out.println("uuid = " + uuid);
            uuidSet.put("uuid", uuid); // cookie加密！

            HttpSession session= request.getSession();
            session.setAttribute("uuid", uuid);

            return new ObjectMapper().writeValueAsString(new HttpResult(200, "登录成功！", null));
        }else{

            // 返回错误结果！
            return new ObjectMapper().writeValueAsString(new HttpResult(400, "登录失败！", null));
        }
    }





}
