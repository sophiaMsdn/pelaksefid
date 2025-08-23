/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import ir.aria.pelaksefid.utility.DateFormatUtil;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author user
 */
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    private static Gson gson = new Gson();

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager,
            JwtConfig jwtConfig,
            SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        setFilterProcessesUrl("/token");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        try {
            UsernamePasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernamePasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword());

            return authenticationManager.authenticate(authentication);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) {

        Date expirationDate = DateFormatUtil.addHourToDate(
                new Date(),
                jwtConfig.getTokenExpirationAfterMinutes());

        String token = jwtConfig.getTokenPrefix() + Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();

        response.addHeader(jwtConfig.getAuthorizationHeader(), token);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(token);
        jwtToken.setExpirationDate(DateFormatUtil.formatDateAsString(expirationDate, DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        jwtToken.setExpirationMinutes(jwtConfig.getTokenExpirationAfterMinutes().toString());

        try {
            response.getWriter().write(gson.toJson(jwtToken));
        } catch (IOException ex) {
            Logger.getLogger(JwtUsernamePasswordAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
