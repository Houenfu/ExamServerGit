package com.itszt.ExamServer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class HttpResult {

    private Integer status;
    private String msg;

    public HttpResult(Integer status, String msg) {
        this.status=status;
        this.msg=msg;
    }
}
