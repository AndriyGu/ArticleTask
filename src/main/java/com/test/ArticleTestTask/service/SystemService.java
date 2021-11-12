package com.test.ArticleTestTask.service;

import com.test.ArticleTestTask.model.Account;
import com.test.ArticleTestTask.model.Role;
import com.test.ArticleTestTask.repository.AccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class SystemService {

    @Autowired
    private final AccountRepository accountRepository;
    private final PasswordService passwordService;

    public SystemService(AccountRepository accountRepository, PasswordService passwordService) {
        this.accountRepository = accountRepository;
        this.passwordService = passwordService;
    }

    @Operation(summary = "method add 1 Users on you DB")
    @GetMapping("/add")
    //TODO delete after tests

    public String registerRoles() {

        try {
            int NUMBER_USERS = 1;
            int NUMBER_ADMIN = 1;
            createUsers(NUMBER_USERS);
            createAdmin(NUMBER_ADMIN);


            return "tables added";
        } catch (Exception ex) {
            return "The DataBase might have a set of test values, and method  " +
                    "\"GET http://localhost:8080/system/add\" " +
                    "was already called   \n \n Error:   " + ex.getMessage();
        }
    }


    //Create admin
    private void createAdmin(int numberAdmin) {
        for (int i = 1; i <= numberAdmin; i++) {
            accountRepository.save(createOneAccount(i, Role.ADMIN));
        }
    }

    //Create users
    private void createUsers(int numberUsers) {
        for (int i = 1; i <= numberUsers; i++) {
            accountRepository.save(createOneAccount(i, Role.USER));
        }
    }



    private Account createOneAccount(int i, Role role) {

        Account account = new Account();
        account.setEmail(i + "_" + role.name() + "@email.com");
        account.setPassword(passwordService.encodePassword("password"));
        account.setRole(role);
        account.setName(i + "__Name");

        return account;
    }
}
