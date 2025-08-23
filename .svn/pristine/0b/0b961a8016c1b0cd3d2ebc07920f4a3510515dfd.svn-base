/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ir.aria.pelaksefid.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import ir.aria.pelaksefid.domain.entity.User;
import ir.aria.pelaksefid.domain.enumaration.RoleEnm;
import ir.aria.pelaksefid.domain.repository.UserRepository;
import ir.aria.pelaksefid.jwt.JwtConfig;
import ir.aria.pelaksefid.service.base.BaseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author Mana2
 */
@Service
public class UserService extends BaseService implements UserDetailsService {

    @Autowired
    private SecretKey secretKey;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = repository.findByUsernameAndIsActive(username, Boolean.TRUE)
                .orElseThrow(()
                        -> new UsernameNotFoundException(String.format("Username %s not found", username))
                );

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : StringUtils.commaDelimitedListToSet(currentUser.getRole())) {
            authorities.addAll(RoleEnm.valueOf(role).getGrantedAuthorities());
        }

        UserDetails user = new org.springframework.security.core.userdetails.User(username, currentUser.getPassword(),
                true, true, true, true,
                authorities);
        return user;
    }

    public User getUserByToken(HttpServletRequest request) {
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
        if (username == null) {
            return null;
        }
        Optional<User> user = repository.findByUsernameAndIsActive(username, true);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }
}
