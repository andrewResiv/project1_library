package ru.andrew.project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrew.project1.models.Book;
import ru.andrew.project1.models.Person;
import ru.andrew.project1.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> showBooksOfPerson(int person_id) {
        return booksRepository.findByOwnerId(person_id);
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findBookById(int book_id) {
        return booksRepository.findById(book_id).orElse(null);
    }

    public Book findBookByName(String book_name) {
        Optional<Book> book = Optional.ofNullable(booksRepository.findByName(book_name));
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setBook_id(id);
        booksRepository.save(book);
    }
    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
    @Transactional
    public void assignBookToPerson(int person_id, int book_id) {
        booksRepository.assignBookToPerson(person_id, book_id);
    }

    @Transactional
    public void unassignBookFromPerson(int book_id) {
        unassignBookFromPerson(book_id);
    }
}
