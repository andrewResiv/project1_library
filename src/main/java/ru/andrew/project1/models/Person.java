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
    private int person_id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=30, message= "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Your name should be Xxxxx Xxxx XXXx")
    private String full_name;
    @Min(value=1900, message = "birthday should be greater then 1900")
    private int birthday;
    public Person(){
    }
    public Person(String full_name, int birthday) {
        this.full_name = full_name;
        this.birthday = birthday;
    }
}
