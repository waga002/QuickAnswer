package com.waga.quickanswer.service;

import com.waga.quickanswer.dto.WechatLoginRequestDTO;

public interface WechatService {
    String getToken(WechatLoginRequestDTO loginRequest) throws Exception;

    void checkToken(String token) throws Exception;
}
