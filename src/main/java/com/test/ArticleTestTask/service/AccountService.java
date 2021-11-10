package com.test.ArticleTestTask.service;

import com.test.ArticleTestTask.model.Account;
import com.test.ArticleTestTask.repository.AccountRepository;
import com.test.ArticleTestTask.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    JwtProvider jwtProvider;
    AccountRepository accountRepository;

    public AccountService(ApplicationEventPublisher applicationEventPublisher, JwtProvider jwtProvider, AccountRepository accountRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.jwtProvider = jwtProvider;
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}


