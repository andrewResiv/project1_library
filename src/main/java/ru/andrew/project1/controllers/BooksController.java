package ru.andrew.project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.andrew.project1.models.Book;
import ru.andrew.project1.models.Person;
import ru.andrew.project1.repositories.BooksRepository;
import ru.andrew.project1.service.BooksService;
import ru.andrew.project1.service.PeopleService;
import ru.andrew.project1.util.BookValidator;
import ru.andrew.project1.util.PersonValidator;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/books")
public class BooksController {

    private final PeopleService peopleService;
    private final BooksService booksService;

    private final BookValidator bookValidator;

    @Autowired
    public BooksController(PeopleService peopleService, BooksService booksService, BookValidator bookValidator) {
        this.peopleService = peopleService;
        this.booksService = booksService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = booksService.findBookById(id);
        model.addAttribute("book", book);

        // Если книга привязана к человеку
        if (book.getOwner() != null) {
            Person assignedPerson = peopleService.findById(book.getOwner().getPerson_id());
            model.addAttribute("assignedPerson", assignedPerson);
        }

        List<Person> people = peopleService.findAll();
        model.addAttribute("people", people);
        return "books/show";


    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/new";
        booksService.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
    // Обработка назначения человека к книге
    @PostMapping("/{id}/assignPerson")
    public String assignPersonToBook(@PathVariable("id") int bookId,
                                     @RequestParam("personId") int personId) {
        booksService.assignBookToPerson(bookId, personId);
        return "redirect:/books/" + bookId;
    }
    @DeleteMapping("/{id}/assignPerson")
    public String deletePersonFromBook(@PathVariable int id) {
        booksService.unassignBookFromPerson(id);
        return "redirect:/books";
    }
}
