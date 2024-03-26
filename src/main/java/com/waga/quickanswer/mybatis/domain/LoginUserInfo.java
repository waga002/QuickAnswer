package com.waga.quickanswer.mybatis.domain;

import lombok.Data;

import java.util.Date;

@Data
public class LoginUserInfo {
    private Integer id;
    private String nickName;
    private byte[] avatarData;
    private String avatarUrl;
    private String openId;
    private String sessionKey;
    private String token;
    private Date tokenCreateTime;
    private String phoneNumber;
    private byte sex;
    private Integer grade;
    private String groupId;
    private byte status;
    private Date createTime;
    private Date updateTime;

//    public LoginUserInfo(String nickName, byte[] avatarData, String avatarUrl, String openId,
//                         String sessionKey, String token, Date tokenCreateTime, String phoneNumber,
//                         byte sex, Integer grade, String groupId, byte status, Date createTime, Date updateTime) {
//        this.nickName = nickName;
//        this.avatarData = avatarData;
//        this.avatarUrl = avatarUrl;
//        this.openId = openId;
//        this.sessionKey = sessionKey;
//        this.token = token;
//        this.tokenCreateTime = tokenCreateTime;
//        this.phoneNumber = phoneNumber;
//        this.sex = sex;
//        this.grade = grade;
//        this.groupId = groupId;
//        this.status = status;
//        this.createTime = createTime;
//        this.updateTime = updateTime;
//    }
        public LoginUserInfo(String nickName, byte[] avatarData, String openId,
                         String token, Date tokenCreateTime, Integer grade, Date createTime, Date updateTime) {
        this.nickName = nickName;
        this.avatarData = avatarData;
        this.openId = openId;
        this.token = token;
        this.tokenCreateTime = tokenCreateTime;
        this.grade = grade;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}

