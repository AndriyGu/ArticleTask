package com.test.ArticleTestTask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
public class Article {

    /**
     * To create an article the user should provide a
     * title,
     * author,
     * the content
     * and date for publishing.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "accountsId")
    private Account account;

    @Lob
    private String content;

    private String autor;
    private LocalDate publicationDate;

    public Article() {
    }

    public Article(String title, Account account, String content, String autor, LocalDate publicationDate) {
        this.title = title;
        this.account = account;
        this.content = content;
        this.autor = autor;
        this.publicationDate = publicationDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
