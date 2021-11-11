package com.test.ArticleTestTask.repository;

import com.test.ArticleTestTask.model.Account;
import com.test.ArticleTestTask.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ArticleRepository extends JpaRepository<Article, Integer> {


    @Query("Select a from Article a WHERE a.publicationDate BETWEEN ?2 and ?1")
    List<Article> findArticlesByLastWeek(LocalDate now, LocalDate ago);

}
