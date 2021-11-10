package com.test.ArticleTestTask.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.ArticleTestTask.model.Account;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class NewArticleDTO {

    /**
     * To create an article the user should provide a
     * title,
     * author,
     * the content
     * and date for publishing.
     */

    private String title;
    private String content;
    private String autor;
    private LocalDateTime publicationDate;

    public NewArticleDTO() {
    }

    public NewArticleDTO(String title, String content, String autor, LocalDateTime publicationDate) {
        this.title = title;
        this.content = content;
        this.autor = autor;
        this.publicationDate = publicationDate;
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

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}
