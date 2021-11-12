package com.test.ArticleTestTask.controller;

import com.test.ArticleTestTask.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    //The endpoint should return count of published articles on daily bases for the 7 days
    static int PERIOD = 7;

    @Autowired
    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "get count of articles")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("getCountArticles")
    ResponseEntity<String> getCountArticles() {
        return adminService.getCountArticlesFromLastWeek();
    }

}