package com.itszt.ExamServer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("question")
public class Question {

    private Integer id;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String answer;
}
