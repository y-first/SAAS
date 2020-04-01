package com.heima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class JwtUtils {
    private static final String KEY = "ihrm"; //签名私钥
    private static final Long TTL = 3000L;//签名失效时间

    /**
     * 设置认证token
     */

    public static String createJwt(String id, String name, Map<String, Object> map) {
        Long now = System.currentTimeMillis();//当前毫秒
        Long exp = now + TTL;

        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(name).signWith(SignatureAlgorithm.HS256, KEY).addClaims(map);
        jwtBuilder.setExpiration(new Date(exp));
        return jwtBuilder.compact();
    }

    public  static Claims parseJwt(String token) {
        Claims body = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        return body;
    }
}
