package com.test.ArticleTestTask.security.jwt;

import com.test.ArticleTestTask.model.Account;
import com.test.ArticleTestTask.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private AccountService accountService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.getAllAccounts().stream().filter(
                acc -> acc.getEmail().equals(username)
        ).findFirst().orElse(null);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(account);
    }



}