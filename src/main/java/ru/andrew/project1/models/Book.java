package ru.andrew.project1.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.stream.DoubleStream;


@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer book_id;


    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=30, message= "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Author should not be empty")
    @Size(min=2, max=30, message= "Author should be between 2 and 30 characters")
    private String author;

    @Min(value=1800, message = "Year should be greater then 1800")
    private int year;

    public Book(){

    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }


    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear( int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
