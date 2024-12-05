package com.appspc.apilibros.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="user")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;

    @Column(name = "username", length = 30)
    private String username;

    @Column(name = "lastname", length = 30)
    private String lastname;

    @Column(name = "doc_type")
    private String doc_type;

    @Column(name = "doc")
    private String doc;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @Column(name = "birth")
    private Date birth;

    @Column(name = "image")
    private String image;

    @Column(name = "bio")
    private String bio;

    @Column(name = "alias", length = 20)
    private String alias;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "rol")
    private Rol rol;

    @Column(name = "clave")
    private Clave clave;
}