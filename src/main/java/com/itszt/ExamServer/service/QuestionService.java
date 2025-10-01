package com.itszt.ExamServer.service;

import com.itszt.ExamServer.entity.Question;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class QuestionService {
    @SneakyThrows
    public void validate(Question question) {

        System.out.println("question = " + question);

        if(StringUtils.isAllBlank(question.getTitle())){
            throw new IllegalAccessException("试题不能为空！");
        }

        if(StringUtils.isAnyBlank(question.getAnswer())){

            throw new IllegalAccessException("答案不能为空！");
        }

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(question.getOptionA());
        hashSet.add(question.getOptionB());
        hashSet.add(question.getOptionC());
        hashSet.add(question.getOptionD());

        if(hashSet.size()<4){

            throw new IllegalAccessException("选项不能重复！");
        }

    }
}
