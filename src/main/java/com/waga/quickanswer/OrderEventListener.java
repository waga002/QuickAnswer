package com.waga.quickanswer;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener implements ApplicationListener<OrderEvent> {
    @Override
    public void onApplicationEvent(OrderEvent event) {
        String msg = event.getMsg();
        System.out.println("【OrderEventListener】监听器处理: " + msg);
    }
}
