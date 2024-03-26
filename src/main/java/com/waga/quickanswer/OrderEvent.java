package com.waga.quickanswer;

import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {
    private String msg;

    public OrderEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
