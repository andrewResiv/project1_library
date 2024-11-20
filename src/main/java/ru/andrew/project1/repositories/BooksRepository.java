package ru.andrew.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.andrew.project1.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    // Метод для получения всех книг по ID владельца
    List<Book> findByOwnerId(int ownerId);
    Optional<Book> findBookByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.owner.person_id = :person_id WHERE b.book_id = :book_id")
    void assignBookToPerson(@Param("book_id") int book_id, @Param("person_id") int person_id);


    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.owner.person_id = NULL WHERE b.book_id = :book_id")
    void unassignBookFromPerson(@Param("book_id") int book_id);
}