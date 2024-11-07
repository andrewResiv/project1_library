package ru.andrew.project1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=30, message= "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Author should not be empty")
    @Size(min=2, max=30, message= "Author should be between 2 and 30 characters")
    private String author;

    @Min(value=1900, message = "Year should be greater then 0")
    private int year;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Book(){

    }
    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }
}
