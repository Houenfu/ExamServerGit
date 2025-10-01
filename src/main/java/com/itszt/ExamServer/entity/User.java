package com.itszt.ExamServer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("springBootTables")
public class User{
    private Integer id;
    private String username;
    private String password;
    private Byte deleted;
}
