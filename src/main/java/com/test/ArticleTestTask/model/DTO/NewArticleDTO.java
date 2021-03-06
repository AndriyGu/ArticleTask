package com.test.ArticleTestTask.model.DTO;

import java.time.LocalDate;

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
    private LocalDate publicationDate;

    public NewArticleDTO() {
    }

    public NewArticleDTO(String title, String content, String autor, LocalDate publicationDate) {
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

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
