package com.appspc.apilibros.data.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class UserBook {
    
    private Integer id;
    private Integer user_id;
    private Integer book_id;
    private String bookName;
    private Integer progress;
    private Integer rating;
    private Boolean isFav;
}
