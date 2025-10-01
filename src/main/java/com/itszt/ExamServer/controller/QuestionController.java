package com.itszt.ExamServer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itszt.ExamServer.entity.HttpResult;
import com.itszt.ExamServer.entity.Question;
import com.itszt.ExamServer.mapper.QuestionMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionMapper questionMapper;

    @SneakyThrows
    @GetMapping("rand")
    public String randomQuestion(){

        List<Question> questions = questionMapper.selectList(null);

        System.out.println("questions = " + questions);
        Collections.shuffle(questions);
        questions.subList(0, 3);

        return new ObjectMapper().writeValueAsString(new HttpResult(200, "随机出题成功！", questions));
    }

    @SneakyThrows
    @PostMapping("score")
    public String getScore(@RequestBody List<Question> questions){

        System.out.println("questions = " + questions);
        int total=0;
        for (int i = 0; i < questions.size(); i++) {

            QueryWrapper<Question> wrapper = new QueryWrapper<>();
            wrapper.eq("id", questions.get(i).getId()).eq("answer", questions.get(i).getAnswer());
            if (questionMapper.selectOne(wrapper)!=null) {

                total+=10;
            }
        }

        return new ObjectMapper().writeValueAsString(new HttpResult(200, "出分成功！", total));
    }
}
