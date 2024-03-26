package com.waga.quickanswer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.waga.quickanswer.dto.WechatLoginRequestDTO;
import com.waga.quickanswer.utils.HttpClientUtil;
import org.apache.http.util.TextUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class WechatServiceImpl implements WechatService {
    private static final String REQUEST_URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String APPID = "wxec1532ce1c3008dd";
    private static final String SECRET = "517952661a4155d8f3c60b357ac34746";
    private static final String GRANT_TYPE = "authorization_code";

    private static String tokenFlag = "";

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public String getToken(WechatLoginRequestDTO loginRequest) throws Exception {
        JSONObject sessionKeyOpenId = getSessionKeyOrOpenId(loginRequest.getCode());
        System.out.println("微信返回的openid和sessionKey: " + sessionKeyOpenId);
        // 获取openId && sessionKey
        String openId = sessionKeyOpenId.getString("openid");
        //String sessionKey = sessionKeyOpenId.getString("session_key");
        // 获取当前时间戳
        long timestamp = Instant.now().getEpochSecond();
        // 拼接openid和时间戳
        String tokenData = openId + timestamp;
        // 使用SHA-256哈希算法对数据进行加密
        String token = getSHA256(tokenData);
        redisTemplate.opsForValue().set(token, 1);
        tokenFlag = token;

        return token;
    }

    @Override
    public void checkToken(String token) throws Exception {
/*        if(redisTemplate.opsForValue().get(token) == null) {
            throw new Exception("用户令牌不存在");
        }*/
        if(TextUtils.isEmpty(tokenFlag)) {
            throw new Exception("用户令牌不存在");
        }
    }

    //调用微信接口服务获取openId && sessionKey
    private JSONObject getSessionKeyOrOpenId(String code) throws Exception {
        Map<String, String> requestUrlParam = new HashMap<>();
        // 小程序appId，自己补充
        requestUrlParam.put("appid", APPID);
        // 小程序secret，自己补充
        requestUrlParam.put("secret", SECRET);
        // 小程序端返回的code
        requestUrlParam.put("js_code", code);
        // 默认参数
        requestUrlParam.put("grant_type", GRANT_TYPE);
        // post请求读取调用微信接口获取openid用户唯一标识
        String result = HttpClientUtil.doPost(REQUEST_URL, requestUrlParam);
        return JSON.parseObject(result);
    }

    private static String getSHA256(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
