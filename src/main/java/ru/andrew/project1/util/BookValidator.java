package ru.andrew.project1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.andrew.project1.dao.BookDAO;
import ru.andrew.project1.dao.PersonDAO;
import ru.andrew.project1.models.Book;
import ru.andrew.project1.models.Person;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> aCLass) {
        return Book.class.equals(aCLass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        //Посмотреть есть ли такой человек в бд
        if (bookDAO.show(book.getName()).isPresent()){
            errors.rejectValue("name", "", "This Book is already in use");
        }
    }
}