package com.example.bookstoreapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("S")
public class Student extends User{
    @ManyToOne
    @JoinColumn(name = "subscriber_id",referencedColumnName = "id")
    private Author author;
    @OneToMany(mappedBy = "student")
    Set<Book>books;
}

