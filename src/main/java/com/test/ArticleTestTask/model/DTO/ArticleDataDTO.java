package com.test.ArticleTestTask.model.DTO;

import java.time.LocalDate;

public class ArticleDataDTO {

    int idArticle;
    int publisherId;
    private String title;
    private String content;
    private String autor;
    private LocalDate publicationDate;

    public ArticleDataDTO() {
    }

    public ArticleDataDTO(int idArticle, int publisherId, String title, String content, String autor, LocalDate publicationDate) {
        this.idArticle = idArticle;
        this.publisherId = publisherId;
        this.title = title;
        this.content = content;
        this.autor = autor;
        this.publicationDate = publicationDate;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
