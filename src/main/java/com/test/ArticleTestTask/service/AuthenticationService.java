package com.test.ArticleTestTask.service;


import com.test.ArticleTestTask.exception.JwtAuthenticationException;
import com.test.ArticleTestTask.model.Account;
import com.test.ArticleTestTask.model.DTO.LoginDTO;
import com.test.ArticleTestTask.repository.AccountRepository;
import com.test.ArticleTestTask.security.jwt.CustomUserDetails;
import com.test.ArticleTestTask.security.jwt.JwtProvider;
import com.test.ArticleTestTask.security.jwt.cache.event.OnUserLogoutSuccessEvent;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;


@Service
public class AuthenticationService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepository;
    JwtProvider jwtProvider;

    public AuthenticationService(AccountRepository accountRepository, JwtProvider jwtProvider) {
        this.accountRepository = accountRepository;
        this.jwtProvider = jwtProvider;
    }

    public String login(LoginDTO request) throws AuthenticationException {
        Account account = accountRepository.findAccountByEmail(request.getEmail());

        if (account == null) {
            throw new AuthenticationException("Email is incorrect");
        }
        if (new BCryptPasswordEncoder().matches(request.getPassword(), account.getPassword())) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtProvider.generateAuthToken(authentication);
        }
        throw new AuthenticationException("Password is incorrect");
    }

    public String logout(HttpServletRequest request, CustomUserDetails account) {
        String token = jwtProvider.getTokenFromRequest(request);
        OnUserLogoutSuccessEvent logoutEventPublisher = new OnUserLogoutSuccessEvent(account.getUsername(), token);
        applicationEventPublisher.publishEvent(logoutEventPublisher);
        return "You have successfully logout";
    }

    public String checkExpiration(String token) {
        Date expireDateFromToken = jwtProvider.getExpireDateFromToken(token);
        try {
            if (expireDateFromToken.after(Date.from(Instant.now()))) {
                return "Token is valid";
            }
        } catch (ExpiredJwtException e) {
            throw new JwtAuthenticationException("Token is invalid");
        }
        throw new JwtAuthenticationException("Token is invalid");
    }

}
