package com.appspc.apilibros.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "bookname")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "gender")
    private String gender;

    @Column(name = "url_image")
    private String url_image;

    @Column(name = "url_pdf")
    private String url_pdf;

    @Column(name = "url_audio")
    private String url_audio;

    @Column(name = "description")
    private String description;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "category")
    private Category category;
}