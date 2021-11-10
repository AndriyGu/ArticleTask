package com.test.ArticleTestTask.service;

import com.test.ArticleTestTask.model.DTO.NewArticleDTO;
import com.test.ArticleTestTask.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public ResponseEntity<String> addNewArticle(@RequestBody NewArticleDTO request,
                                                HttpServletRequest req) {
        if (request.getTitle().isEmpty() ||
                request.getContent().isEmpty() ||
                request.getAutor().isEmpty() ||
                request.getPublicationDate().toString().isEmpty()) {
            return new ResponseEntity<String>("All of the fields are mandatory", HttpStatus.BAD_REQUEST);
        }
        if (request.getTitle().length() > 50) {
            return new ResponseEntity<String>("title should not exceed 100 characters", HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
