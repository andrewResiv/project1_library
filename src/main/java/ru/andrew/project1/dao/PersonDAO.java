package ru.andrew.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.andrew.project1.models.Person;

import java.util.List;
import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> show(String name){
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?",
                new Object[] {name}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
       }

    public void save(Person person) {

        jdbcTemplate.update("INSERT INTO Person(name, birthday) VALUES (?, ?)",
                person.getFullName(), person.getBirthday());
    }

    public void update(int person_id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, birthday=? WHERE person_id=?",
                updatePerson.getFullName(), updatePerson.getBirthday(), person_id);
    }

    public void delete(int person_id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", person_id);
    }
}