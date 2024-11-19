package ru.andrew.project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.andrew.project1.models.Book;
import ru.andrew.project1.models.Person;
import ru.andrew.project1.service.BooksService;
import ru.andrew.project1.service.PeopleService;
import ru.andrew.project1.util.PersonValidator;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonValidator personValidator;

    private final PeopleService peopleService;
    private final BooksService booksService;


    @Autowired
    public PeopleController(PersonValidator personValidator, PeopleService peopleService, BooksService booksService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(Model model) {
        List<Person> people = peopleService.findAll();
        model.addAttribute("people", people);
        return "people/index";
    }

    @GetMapping("/{person_id}")
    public String show(@PathVariable("person_id") int personId, Model model) {
        Person person = peopleService.findById(personId);
        List<Book> books = booksService.showBooksOfPerson(personId);
        model.addAttribute("books", books);
        if (person == null) {
            return "redirect:/error";  // Перенаправление на страницу ошибки, если человек не найден
        }
        model.addAttribute("person", person);
        return "/people/show";  // Страница отображения информации о человеке
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        System.out.println(person.getFull_name());
        if (bindingResult.hasErrors())
            return "people/new";
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{person_id}/edit")
    public String edit(Model model, @PathVariable("person_id") int person_id) {
        model.addAttribute("person", peopleService.findById(person_id));
        return "people/edit";
    }

    @PatchMapping("/{person_id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("person_id") int person_id) {
        if (bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(person_id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{person_id}")
    public String delete(@PathVariable int person_id) {
        peopleService.delete(person_id);
        return "redirect:/people";
    }
}
