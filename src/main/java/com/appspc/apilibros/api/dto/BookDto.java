package com.appspc.apilibros.api.dto;

public class BookDto {

    private Integer id;
    private String bookName;
    private String author;
    private String gender;
    private String url_image;
    private String url_pdf;
    private String url_audio;
    private String description;
    private Boolean isActive;
    private CategoryDto categoryBook;


    public BookDto() {
    }

    public BookDto(Integer id, String bookName, String author, String gender, String url_image,String url_pdf, String url_audio, String description, Boolean isActive, CategoryDto categoryDto) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.gender = gender;
        this.url_audio= url_audio;
        this.url_image= url_image;
        this.url_pdf=url_pdf;
        this.description=description;
        this.isActive=isActive;
        this.categoryBook = categoryDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

   
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
 
    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }


    public String getUrl_pdf() {
        return url_pdf;
    }

    public void setUrl_pdf(String url_pdf) {
        this.url_pdf = url_pdf;
    }

    public String getUrl_audio() {
        return url_audio;
    }

    public void setUrl_audio(String url_audio) {
        this.url_audio = url_audio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public CategoryDto getCategoryDto(){
        return categoryBook;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryBook = categoryDto;
    }
}