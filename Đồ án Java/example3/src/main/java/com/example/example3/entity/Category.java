package com.example.example3.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;
    
    @Column(nullable = true)
    private String slug;

    @Column(nullable = true)
    private String photo;

    @Column(nullable = true)
    private int parent_id;

    @Column(nullable = true)
    private int sort_order;

    @Column(nullable = true)
    private int status;

    @JsonIgnore
    @OneToMany
    private List<Product> products;
}


//npm install @mui/styles  --force