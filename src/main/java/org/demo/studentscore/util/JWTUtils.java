package org.demo.studentscore.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JWTUtils {
    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    @Value("${jwt.expireTime}")
    private Long exp;

    /**
     * 设置多个值
     *
     * @param map 传入多个键值对map集合
     * @return 返回token字符串
     */
    public String createToken(Map<String, Object> map) {
        byte[] secretKey = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        return Jwts
                .builder()
                .setClaims(map)
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .compact();
    }


    /**
     * 设置单个键值对值
     *
     * @param key   键值对键
     * @param value 键值对值
     * @return 返回token字符串
     */

    public String createToken(String key, Object value) {
        byte[] secretKey = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        return Jwts
                .builder()
                .claim(key, value)
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .compact();
    }


    public Claims parseToken(String token) {
        byte[] secretKey = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isExpired(String token) {
        byte[] secretKey = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return true;
        }
    }
}
