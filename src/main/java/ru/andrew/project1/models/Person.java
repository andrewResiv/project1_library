package ru.andrew.project1.models;


import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.validation.constraints.*;

/**
 * @author Neil Alishev
 */


public class Person {

//    KeyHolder keyHolder = new GeneratedKeyHolder();
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=30, message= "Name should be between 2 and 30 characters")
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, [A-Z]\\w+", message = "Your name should be Xxxxx Xxxx XXXx")
    private String fullName;
    @Min(value=1900, message = "Year should be greater then 0")
    private int birthday;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String full_name) {
        this.fullName = full_name;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public Person(){
    }

    public Person(String full_name, int birthday) {
        this.fullName = full_name;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }
}
