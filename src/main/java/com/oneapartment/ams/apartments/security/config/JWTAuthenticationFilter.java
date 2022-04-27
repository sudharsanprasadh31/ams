package com.oneapartment.ams.apartments.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.oneapartment.ams.apartments.dto.JwtToken;
import com.oneapartment.ams.apartments.entity.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.oneapartment.ams.apartments.security.config.SecurityConstants.EXPIRATION_TIME;
import static com.oneapartment.ams.apartments.security.config.SecurityConstants.SECRET;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private Gson gson = new Gson();

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            AppUser creds = new ObjectMapper()
                    .readValue(req.getInputStream(), AppUser.class);

            log.info("The User Credentials are Username/emailAddress: " + creds.getUsername() + " password: " + creds.getPassword());

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String token = JWT.create()
                .withSubject(((AppUser) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        log.info("The token is:" + token);
        JwtToken jwtToken  = new JwtToken(token);
        String resToken = this.gson.toJson(jwtToken);

        //String body = ((AppUser) auth.getPrincipal()).getUsername() + " " + token;

        res.setContentType("application/json");
        res.getWriter().write(resToken);
        res.getWriter().flush();
    }
}
