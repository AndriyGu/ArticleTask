package com.test.ArticleTestTask.controller;

import com.test.ArticleTestTask.model.Account;
import com.test.ArticleTestTask.model.DTO.ArticleDataDTO;
import com.test.ArticleTestTask.model.Role;
import com.test.ArticleTestTask.repository.AccountRepository;
import com.test.ArticleTestTask.service.PasswordService;
import com.test.ArticleTestTask.service.SystemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/system")
public class SystemController {

    SystemService systemService;

    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @Operation(summary = "method add 1 Users on you DB")
    @GetMapping("/add")
    //TODO delete after tests

    String registerRoles(){
        return systemService.registerRoles();
    }
}
