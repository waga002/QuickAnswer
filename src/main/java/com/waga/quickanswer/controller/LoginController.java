package com.waga.quickanswer.controller;

import com.waga.quickanswer.dto.WechatLoginRequestDTO;
import com.waga.quickanswer.http.HttpResponse;
import com.waga.quickanswer.http.StatusCode;
import com.waga.quickanswer.service.WechatService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class LoginController {

    @Resource
    WechatService wechatService;

    @PostMapping("/wxlogin")
    public HttpResponse login(@Valid @RequestBody WechatLoginRequestDTO loginRequest) {
        System.out.println("收到微信code：" + loginRequest.getCode());
        HttpResponse response = new HttpResponse(StatusCode.Success);
        try {
            String token = wechatService.getToken(loginRequest);
            System.out.println("返回给微信的token：" + token);
            response.setData(token);
        } catch (Exception e) {
            response = new HttpResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    @GetMapping("/checkwxlogin")
    public HttpResponse checkLogin(@NotNull @RequestParam String token) {
        System.out.println("收到待校验token：" + token);
        HttpResponse response = new HttpResponse(StatusCode.Success);
        try {
            wechatService.checkToken(token);
        } catch (Exception e) {
            response = new HttpResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }
}
