package com.test.ArticleTestTask.controller;

import com.test.ArticleTestTask.model.DTO.NewArticleDTO;
import com.test.ArticleTestTask.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //add article
    @Operation(summary = "add article")
    @PostMapping("/updateMenteeByEmail")
    public ResponseEntity<String> updateMenteeByToken(@RequestBody NewArticleDTO request,
                                                      HttpServletRequest req) {

        return articleService.addNewArticle(request, req);
    }
}
