package com.test.ArticleTestTask.controller;

import com.test.ArticleTestTask.model.DTO.ArticleDataDTO;
import com.test.ArticleTestTask.model.DTO.NewArticleDTO;
import com.test.ArticleTestTask.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PostMapping("/addNewArticle")
    public ResponseEntity<String> addNewArticle(@RequestBody NewArticleDTO request,
                                                HttpServletRequest req) {
        //TODO create wey to send message to user when date is wrong
        return articleService.addNewArticle(request, req);
    }

    @Operation(summary = "get articles")
    @GetMapping("getArticles/{currentPage}/{pageSize}")
    ResponseEntity<Page<ArticleDataDTO>> getArticleFroLastWeek(@PathVariable("currentPage") int currentPage,
                                                               @PathVariable("pageSize") int pageSize
    ) {
        return articleService.getArticles(currentPage, pageSize);
    }
}
