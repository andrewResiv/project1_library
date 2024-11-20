package ru.andrew.project1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.andrew.project1.models.Person;
import ru.andrew.project1.service.PeopleService;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;
    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aCLass) {
        return Person.class.equals(aCLass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        //Посмотреть есть ли такой человек в бд
        if (peopleService.findByFullName(person.getFull_name()).isPresent()){
            errors.rejectValue("full_name", "", "This name is already in use");
        }
    }
}