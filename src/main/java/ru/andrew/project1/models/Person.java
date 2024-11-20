package ru.andrew.project1.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

/**
 * @author Neil Alishev
 */

@Setter
@Getter
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + person_id +
                ", name='" + full_name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
