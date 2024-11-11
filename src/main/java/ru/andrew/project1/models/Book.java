package ru.andrew.project1.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.stream.DoubleStream;

@Setter
@Getter
public class Book {
    private int book_id;
    private int person_id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=30, message= "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Author should not be empty")
    @Size(min=2, max=30, message= "Author should be between 2 and 30 characters")
    private String author;

    @Min(value=1900, message = "Year should be greater then 0")
    private int year;

    public Book(){

    }
    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }
}
