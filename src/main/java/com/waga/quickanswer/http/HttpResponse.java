package com.waga.quickanswer.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public HttpResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public HttpResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public HttpResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
