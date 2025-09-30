package com.itszt.ExamServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itszt.ExamServer.mapper")
public class MainServer {

    public static void main(String[] args) {

        SpringApplication.run(MainServer.class);
    }
}
