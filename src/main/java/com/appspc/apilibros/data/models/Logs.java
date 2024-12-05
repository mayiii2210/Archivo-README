package com.appspc.apilibros.data.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="logs")
@Data
@NoArgsConstructor
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "error_stack", unique = true)
    private String error_stack;

    @Column(name = "timestamp", unique = true)
    private Date timestamp;

    @Column(name = "description", unique = true)
    private String description;
    
}
