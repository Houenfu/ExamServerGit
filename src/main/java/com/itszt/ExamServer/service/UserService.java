package com.itszt.ExamServer.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itszt.ExamServer.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itszt.ExamServer.entity.User;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void validate(String username, String password) {

        if (StringUtils.isAnyBlank(username, password)) {
            throw new IllegalArgumentException("账号或密码不能为空！");
        }

        if(username.length()>20){

            throw new IllegalArgumentException("用户名长度不能超过20！");
        }
    }

    public User getUser(String username, String password) {

        System.out.println("username = " + username + ", password = " + password);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", password);

        User user = userMapper.selectOne(wrapper);
        System.out.println("user = " + user);
        System.out.println("-------------------------------------------");
        return user;
    }
}
