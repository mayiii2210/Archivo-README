package com.appspc.apilibros.api.dto;

public class UserBookDto {
    private Integer id;
    private Integer user_id;
    private Integer book_id;
    private String bookName;
    private Integer progress;
    private Integer rating;
    private Boolean isFav;

    public UserBookDto() {
    }

    public UserBookDto(Integer id, Integer user_id, Integer book_id, String bookName, Integer progress, Integer rating, Boolean isFav) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.bookName = bookName;
        this.progress = progress;
        this.rating = rating;
        this.isFav = isFav;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getIsFav() {
        return isFav;
    }

    public void setIsFav(Boolean isFav) {
        this.isFav = isFav;
    }
}

