package com.appspc.apilibros.data.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "clave")
@Data
public class Clave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "HashClave")
    private String hashClave;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "modification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modification;

}
