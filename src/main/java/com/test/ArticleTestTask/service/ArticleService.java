package com.test.ArticleTestTask.service;

import com.test.ArticleTestTask.model.Account;
import com.test.ArticleTestTask.model.Article;
import com.test.ArticleTestTask.model.DTO.NewArticleDTO;
import com.test.ArticleTestTask.repository.AccountRepository;
import com.test.ArticleTestTask.repository.ArticleRepository;
import com.test.ArticleTestTask.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;
    JwtProvider jwtProvider;
    AccountRepository accountRepository;

    public ArticleService(ArticleRepository articleRepository, JwtProvider jwtProvider, AccountRepository accountRepository) {
        this.articleRepository = articleRepository;
        this.jwtProvider = jwtProvider;
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<String> addNewArticle(@RequestBody NewArticleDTO request,
                                                HttpServletRequest req) {
        String token = jwtProvider.getTokenFromRequest(req);
        int accountId = jwtProvider.getIdFromToken(token);

        if (request.getTitle().isEmpty() ||
                request.getContent().isEmpty() ||
                request.getAutor().isEmpty() ||
                request.getPublicationDate().toString().isEmpty()) {
            return new ResponseEntity<String>("All of the fields are mandatory", HttpStatus.BAD_REQUEST);
        }
        if (request.getTitle().length() > 50) {
            return new ResponseEntity<String>("Title should not exceed 100 characters", HttpStatus.BAD_REQUEST);
        }

        String date = request.getPublicationDate().toString();
        try {
            DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
            System.out.println("Validating " + date + " as date");
            df.parse(date);
        } catch (ParseException ex) {
            // not yyyy-mm-dd date.
            System.out.println(": Bad");
            System.out.println(date + " cannot be parsed as a date. format: yyyy-mm-dd.");
        }


        Article article = new Article();
        article.setAccount(accountRepository.getById(accountId));
        article.setAutor(request.getAutor());
        article.setContent(request.getContent());
        article.setPublicationDate(request.getPublicationDate());
        article.setTitle(request.getTitle());

        articleRepository.save(article);

        return new ResponseEntity<String>("Article added", HttpStatus.OK);

    }
}
