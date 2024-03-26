package com.waga.quickanswer.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WechatLoginRequestDTO {
    @NotNull(message = "code不能为空")
    private String code;  //微信code
}