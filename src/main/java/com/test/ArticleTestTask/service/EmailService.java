package com.test.ArticleTestTask.service;

import com.test.ArticleTestTask.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    AccountRepository accountRepository;

    public EmailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //check user email is existing in database
    public boolean emailExist(String email) {
        return accountRepository.findByEmailOptional(email).isPresent();
    }

}
