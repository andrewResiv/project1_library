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
    List<Book> findByOwner_personId(int ownerId);

    Optional<Book> findBookByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.owner.personId = :personId WHERE b.bookId = :bookId")
    void assignBookToPerson(@Param("bookId") int bookId, @Param("personId") int personId);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.owner = NULL WHERE b.bookId = :bookId")
    void unassignBookFromPerson(@Param("bookId") int bookId);
}
