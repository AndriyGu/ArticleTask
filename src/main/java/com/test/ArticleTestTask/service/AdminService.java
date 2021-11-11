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
    AccountService accountService;

    public AdminService(ArticleRepository articleRepository, AccountRepository accountRepository, AccountService accountService) {
        this.articleRepository = articleRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    public ResponseEntity<Page<ArticleDataDTO>> getArticleFroLastWeek(int currentPage, int pageSize) {
        LocalDate dateBefore = (LocalDate.now()).minusDays(7);
        LocalDate dateNow = (LocalDate.now());

        List<Article> articleList = articleRepository.findArticlesByLastWeek(dateNow, dateBefore);
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
