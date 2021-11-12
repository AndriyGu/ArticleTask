package com.test.ArticleTestTask.service;

import com.test.ArticleTestTask.model.Account;
import com.test.ArticleTestTask.model.Article;
import com.test.ArticleTestTask.model.DTO.ArticleDataDTO;
import com.test.ArticleTestTask.model.DTO.NewArticleDTO;
import com.test.ArticleTestTask.repository.AccountRepository;
import com.test.ArticleTestTask.repository.ArticleRepository;
import com.test.ArticleTestTask.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.*;

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

    public ResponseEntity<Page<ArticleDataDTO>> getArticles(int currentPage, int pageSize) {
        List<Article> articleList = articleRepository.findAllArticles();
        List<ArticleDataDTO> articleDataDTOList = new ArrayList<>();

        if (articleList.size() > 0) {
            for (Article temp : articleList) {
                ArticleDataDTO tempDTO = new ArticleDataDTO();
                tempDTO.setIdArticle(temp.getId());
                tempDTO.setPublisherId(temp.getAccount().getId());
                tempDTO.setTitle(temp.getTitle());
                tempDTO.setAutor(temp.getAutor());
                tempDTO.setContent(temp.getContent());
                tempDTO.setPublicationDate(temp.getPublicationDate());
                articleDataDTOList.add(tempDTO);
            }


            Page<ArticleDataDTO> smallList = findPaginated(articleDataDTOList, PageRequest.of(currentPage - 1, pageSize));

            return new ResponseEntity<Page<ArticleDataDTO>>(smallList, HttpStatus.OK);

        }
        // like else
        List<ArticleDataDTO> list = new ArrayList<>();
        Page<ArticleDataDTO> smallDTOPagination = new PageImpl<ArticleDataDTO>(list, PageRequest.of(currentPage, pageSize), articleDataDTOList.size());
        return new ResponseEntity<Page<ArticleDataDTO>>(smallDTOPagination, HttpStatus.NOT_FOUND);

    }


    private Page<ArticleDataDTO> findPaginated(List<ArticleDataDTO> articleDataDTOList, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ArticleDataDTO> list;

        if (articleDataDTOList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, articleDataDTOList.size());
            list = articleDataDTOList.subList(startItem, toIndex);
        }
        Page<ArticleDataDTO> bookPage
                = new PageImpl<ArticleDataDTO>(list, PageRequest.of(currentPage, pageSize), articleDataDTOList.size());
        return bookPage;
    }
}
