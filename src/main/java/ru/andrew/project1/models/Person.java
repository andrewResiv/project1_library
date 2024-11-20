package ru.andrew.project1.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

/**
 * @author Neil Alishev
 */


@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer person_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=100, message= "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+", message = "Your name should be Петров Петр Петрович")
    @Column(name = "full_name")
    private String full_name;

    @Min(value=1900, message = "birthday should be greater then 1900 and less then 2025")
    @Max(value = 2025, message = "birthday should be greater then 1900 and less then 2025")
    @Column(name = "birthday")
    private int birthday;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(){
    }
    public Person(String full_name, int birthday) {
        this.full_name = full_name;
        this.birthday = birthday;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public @NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 100, message = "Name should be between 2 and 30 characters") @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+", message = "Your name should be Петров Петр Петрович") String getFull_name() {
        return full_name;
    }

    public void setFull_name(@NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 100, message = "Name should be between 2 and 30 characters") @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+", message = "Your name should be Петров Петр Петрович") String full_name) {
        this.full_name = full_name;
    }

    @Min(value = 1900, message = "birthday should be greater then 1900 and less then 2025")
    @Max(value = 2025, message = "birthday should be greater then 1900 and less then 2025")
    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(@Min(value = 1900, message = "birthday should be greater then 1900 and less then 2025") @Max(value = 2025, message = "birthday should be greater then 1900 and less then 2025") int birthday) {
        this.birthday = birthday;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + person_id +
                ", name='" + full_name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
