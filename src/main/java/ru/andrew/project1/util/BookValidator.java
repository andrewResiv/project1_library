package ru.andrew.project1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.andrew.project1.models.Book;
import ru.andrew.project1.service.BooksService;

@Component
public class BookValidator implements Validator {

    private final BooksService booksService;

    @Autowired
    public BookValidator(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public boolean supports(Class<?> aCLass) {
        return Book.class.equals(aCLass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        //Посмотреть есть ли такая книга в бд
        if (booksService.findBookByName(book.getName()).isPresent()) {
            errors.rejectValue("name", "", "This Book is already in use");
        }
    }
}