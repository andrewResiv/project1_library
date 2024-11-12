package ru.andrew.project1.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;
import ru.andrew.project1.models.Book;

import java.util.List;
import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int book_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?", new Object[]{book_id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Book> show(String name){
        return jdbcTemplate.query("SELECT * FROM Book WHERE name=?",
                new Object[] {name}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public List<Book> showBooksOfPerson(int person_id) {
        String sql = "SELECT * FROM Book WHERE person_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), person_id);
    }

    public Integer save(Book book) {
        String sql = "INSERT INTO Book (name, author, year) VALUES (?, ?, ?) RETURNING book_id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, new Object[]{book.getName(), book.getAuthor(), book.getYear()}, Integer.class);
        return generatedId != null ? generatedId : 0;
    }

    public void update(int book_id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE book_id=?",
                updateBook.getName(), updateBook.getAuthor(), updateBook.getYear(), book_id);
    }

    public void delete(int book_id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", book_id);
    }

    // Назначение человека к книге
    public void assignPersonToBook(int bookId, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE book_id = ?", personId, bookId);
    }

    // Снятие привязки человека от книги
    public void unassignPersonFromBook(int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE book_id = ?", bookId);
    }

}