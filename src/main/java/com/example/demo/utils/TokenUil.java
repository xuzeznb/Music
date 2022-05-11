package com.example.demo.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
/**
* 生成token
* @return
*/

import java.util.Date;

public class TokenUil {
     public static String genToken(String userId, String sign ){
     return JWT.create().withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))
                .sign(Algorithm.HMAC256(sign));
    }



}
