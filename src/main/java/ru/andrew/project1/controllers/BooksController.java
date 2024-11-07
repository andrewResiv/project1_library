package ru.andrew.project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.andrew.project1.dao.BookDAO;
import ru.andrew.project1.models.Book;
import ru.andrew.project1.util.PersonValidator;

import javax.validation.Valid;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonValidator personValidator;

    @Autowired
    public BooksController(BookDAO personDAO, PersonValidator personValidator) {
        this.bookDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("books", bookDAO.show(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        personValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
