/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.service.base;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import ir.aria.pelaksefid.domain.entity.User;
import ir.aria.pelaksefid.domain.entity.base.Base;
import ir.aria.pelaksefid.domain.model.LogModelDto;
import ir.aria.pelaksefid.jwt.JwtConfig;
import ir.aria.pelaksefid.service.UserService;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author user
 */
public class BaseService {

    @Autowired
    private SecretKey secretKey;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private UserService userService;

    public Long oneDayMilliSeconds = 86400000L;

    public Base logEntity(Base e, HttpServletRequest request) {

        String username = null;
        try {
            String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
            String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            username = body.getSubject();
        } catch (Exception ex) {
        }
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String browserName = request.getHeader("User-Agent");

        LogModelDto log = new LogModelDto(username, ipAddress, browserName);

        return logEntity(e, log);
    }

    private Base logEntity(Base e, LogModelDto log) {
        e.setUserIp(log.getUserIp());
        e.setUserId(log.getUsername());
        e.setChannelId(log.getChannelId());
        if (e.getRegDate() == null) {
            e.setRegDate(log.getRegisterDate());
        }
        e.setUpdateDate(log.getUpdateDate());
        if (e.getIsAlive() == null) {
            e.setIsAlive(Boolean.TRUE);
        }
        if (e.getIsActive() == null) {
            e.setIsActive(Boolean.TRUE);
        }
        return e;
    }

    public User getUserByToken(HttpServletRequest request) {

        return userService.getUserByToken(request);
    }

}
