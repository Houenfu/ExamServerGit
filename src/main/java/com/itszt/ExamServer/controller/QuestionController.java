package com.itszt.ExamServer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itszt.ExamServer.Question.Question;
import com.itszt.ExamServer.entity.HttpResult;
import com.itszt.ExamServer.mapper.QuestionMapper;
import com.itszt.ExamServer.service.QuestionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

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
}
