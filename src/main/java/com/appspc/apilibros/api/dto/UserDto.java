package com.appspc.apilibros.api.dto;

import java.util.Date;

public class UserDto {
    
    private Integer id;
    private String username;
    private String lastname;
    private String doc_type;
    private String doc;
    private String email;
    private String tel;
    private Date birth;
    private String image;
    private String bio;
    private String alias;
    private Boolean isActive;
    private RolDto rolUsr;

    public UserDto() {
    }

    public UserDto(Integer id, String username, String lastname, String doc_type, String doc, String email, String tel, Date birth, String image, String bio, String alias,  Boolean isActive, RolDto rolDto) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.doc_type = doc_type;
        this.doc = doc;
        this.email = email;
        this.tel = tel;
        this.birth = birth;
        this.image = image;
        this.bio = bio;
        this.alias = alias;
        this.isActive = isActive;
        this.rolUsr = rolDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc (String doc) {
        this.doc = doc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public RolDto getRolDto() {
        return rolUsr;
    }

    public void setRolDto(RolDto rolDto) {
        this.rolUsr = rolDto;
    }
}
