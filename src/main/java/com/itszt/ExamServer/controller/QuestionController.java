package com.itszt.ExamServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itszt.ExamServer.Question.Question;
import com.itszt.ExamServer.entity.HttpResult;
import com.itszt.ExamServer.mapper.QuestionMapper;
import com.itszt.ExamServer.service.QuestionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

    // 测试完毕！
    @SneakyThrows
    @PostMapping
    public String add(@RequestBody Question question){

        // 添加试题！
        // 保证title, answer不为空！
        // abcd不得重复
        questionService.validate(question);

        // sql语句执行添加！
        int insert = questionMapper.insert(question);
        System.out.println("insert = " + insert);


        HttpResult httpResult = new HttpResult(200, "试题添加成功！");
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(httpResult);
        System.out.println("json = " + json);

        return json;
    }
}
