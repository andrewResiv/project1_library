package ru.andrew.project1.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.validation.constraints.*;

/**
 * @author Neil Alishev
 */

@Setter
@Getter
public class Person {
    private Integer person_id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=100, message= "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+", message = "Your name should be Петров Петр Петрович")
    private String full_name;
    @Min(value=1900, message = "birthday should be greater then 1900 and less then 2025")
    @Max(value = 2025, message = "birthday should be greater then 1900 and less then 2025")
    private int birthday;
    public Person(){
    }
    public Person(String full_name, int birthday) {
        this.full_name = full_name;
        this.birthday = birthday;
    }
}
