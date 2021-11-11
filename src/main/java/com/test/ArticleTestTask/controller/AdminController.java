package com.test.ArticleTestTask.controller;

import com.test.ArticleTestTask.model.Article;
import com.test.ArticleTestTask.model.DTO.ArticleDataDTO;
import com.test.ArticleTestTask.repository.AccountRepository;
import com.test.ArticleTestTask.repository.ArticleRepository;
import com.test.ArticleTestTask.service.AccountService;
import com.test.ArticleTestTask.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    //The endpoint should return count of published articles on daily bases for the 7 days
    static int PERIOD = 7;

    @Autowired
    ArticleRepository articleRepository;
    AccountRepository accountRepository;
    AccountService accountService;
    AdminService adminService;

    public AdminController(ArticleRepository articleRepository, AccountRepository accountRepository, AccountService accountService, AdminService adminService) {
        this.articleRepository = articleRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.adminService = adminService;
    }

    @Operation(summary = "get articles")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("getArticles/{currentPage}/{pageSize}")
    ResponseEntity<Page<ArticleDataDTO>> getArticleFroLastWeek(@PathVariable("currentPage") int currentPage,
                                                               @PathVariable("pageSize") int pageSize
    ) {
        return adminService.getArticleFroLastWeek(currentPage, pageSize);
    }
}