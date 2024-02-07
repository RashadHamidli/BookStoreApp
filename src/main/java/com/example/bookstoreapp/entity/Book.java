package com.example.bookstoreapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Author author;
    @ManyToOne
    private Student student;
    @OneToMany(mappedBy = "book")
    private Set<User>users;
    private boolean currentlyReading;
}