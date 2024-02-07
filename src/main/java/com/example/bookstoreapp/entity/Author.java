package com.example.bookstoreapp.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("A")
public class Author extends User{
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private Set<Student>students;
    @OneToMany(mappedBy = "author")
    Set<Book>books;
}

