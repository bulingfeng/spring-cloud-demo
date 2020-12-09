package com.bulingfeng.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:bulingfeng
 * @Date: 2020-12-09
 */
public class JwtUtil {
    public static final String SECRET_KEY="bulingfeng";
    // 10秒钟
    public static final Integer EXPIRE_TIME=1000*10;

    public static String sign(String username, String password) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("pwd", password)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 检验token是否正确
     * @param **token**
     * @return
     */
    public static boolean verify(String token,String username,String password){
        try {
            Algorithm algorithm = Algorithm.HMAC256(password+SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username",username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 从token中获取username信息,无需解密
     * @param **token**
     * @return
     */
    public static String getUserName(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            if(System.currentTimeMillis()-jwt.getExpiresAt().getTime()>0){
                return null;
            }
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e){
            e.printStackTrace();
            return null;
        }
    }

    public static Date createDate(){
        Date date=new Date();
        date.setTime(date.getTime()+1000*5);
        return date;
    }



    public static void main(String[] args) {
        String username="blf";
        String password="password";
        String sign = sign(username, password);
        verify(sign,username,password);
        System.out.println(getUserName(sign));
    }
}
