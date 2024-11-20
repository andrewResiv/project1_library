package ru.andrew.project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrew.project1.models.Book;
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
        return booksRepository.findByOwner_personId(person_id);
    }

    public Page<Book> findAll(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }
    public List<Book> findAll() {
        return booksRepository.findAll();  // Возвращает все книги
    }

    public Book findBookById(int book_id) {
        return booksRepository.findById(book_id).orElse(null);
    }

    public Optional<Book> findBookByName(String book_name) {
        return booksRepository.findBookByName(book_name);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setBookId(id);
        booksRepository.save(book);
    }
    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
    @Transactional
    public void assignBookToPerson(int personId, int bookId) {
        booksRepository.assignBookToPerson(personId, bookId);
    }

    @Transactional
    public void unassignBookFromPerson(int bookId) {
        booksRepository.unassignBookFromPerson(bookId);
    }
}
