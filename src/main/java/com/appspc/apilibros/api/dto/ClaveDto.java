package com.appspc.apilibros.api.dto;

import java.util.Date;

public class ClaveDto {

    private Integer id;
    private Integer user_id;
    private String hashClave;
    private Boolean isActive;
    private Date modification;

    public ClaveDto() {
    }

    public ClaveDto(Integer id, Integer user_id, String hashClave, Boolean isActive, Date modification) {
        this.id = id;
        this.user_id = user_id;
        this.hashClave = hashClave;
        this.isActive = isActive;
        this.modification = modification;
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

    public String getHashClave() {
        return hashClave;
    }

    public void setHashClave(String hashClave) {
        this.hashClave = hashClave;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getModification() {
        return modification;
    }

    public void setModification(Date modification) {
        this.modification = modification;
    }
}
