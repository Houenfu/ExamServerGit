package com.itszt.ExamServer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itszt.ExamServer.entity.Question;
import com.itszt.ExamServer.entity.HttpResult;
import com.itszt.ExamServer.mapper.QuestionMapper;
import com.itszt.ExamServer.service.QuestionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    // 测试完毕！
    @SneakyThrows
    @DeleteMapping("/{questionId}")
    public String delete(@RequestBody Question question, @PathVariable Integer questionId){
        System.out.println("question = " + question);
        questionService.validate(question);

        // ---------------------------------------------------
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.eq("id", questionId);

        // ----------------------------------------------------

        int delete = questionMapper.deleteById(wrapper);

        return new ObjectMapper().writeValueAsString(new HttpResult(200, "删除成功！", delete));
    }

    @SneakyThrows
    @GetMapping
    public String list(){

        List<Question> questions = questionMapper.selectList(null);
        HttpResult httpResult = new HttpResult(200, "试题查询成功！", questions);

        String json = new ObjectMapper().writeValueAsString(httpResult);

        System.out.println("json = " + json);

        return json;
    }

    @SneakyThrows
    @PutMapping
    public String update(@RequestBody Question question){

        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.eq("title", question.getTitle()).ne("id", null);

        if(questionMapper.selectOne(wrapper)!=null){

            questionMapper.updateById(question);

            return new ObjectMapper().writeValueAsString(new HttpResult(200, "删除成功！", null));
        }else{

            throw new IllegalAccessException("试题并不存在！");
//            return new ObjectMapper().writeValueAsString(new HttpResult(400, "删除失败！", null));
        }
    }


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
