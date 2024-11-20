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
    private Integer personId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=100, message= "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+", message = "Your name should be Петров Петр Петрович")
    @Column(name = "full_name")
    private String fullName;

    @Min(value=1900, message = "birthday should be greater then 1900 and less then 2025")
    @Max(value = 2025, message = "birthday should be greater then 1900 and less then 2025")
    @Column(name = "birthday")
    private int birthday;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(){
    }
    public Person(String fullName, int birthday) {
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public  String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday ( int birthday) {
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
                "id=" + personId +
                ", name='" + fullName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
