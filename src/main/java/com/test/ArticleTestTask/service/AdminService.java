package com.test.ArticleTestTask.service;

import com.test.ArticleTestTask.model.Article;
import com.test.ArticleTestTask.model.DTO.ArticleDataDTO;
import com.test.ArticleTestTask.repository.AccountRepository;
import com.test.ArticleTestTask.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    ArticleRepository articleRepository;
    AccountRepository accountRepository;

    public AdminService(ArticleRepository articleRepository, AccountRepository accountRepository) {
        this.articleRepository = articleRepository;
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<String> getCountArticlesFromLastWeek() {
        //last week
        LocalDate dateBefore = (LocalDate.now()).minusDays(7);
        LocalDate dateNow = (LocalDate.now());

        int articleCount = articleRepository.findArticlesCountByLastWeek(dateNow, dateBefore);
        String res = String.format("From last seven days was add %s articles", articleCount);

        return new ResponseEntity<String>(res, HttpStatus.OK);

    }
}
