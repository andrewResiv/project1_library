package ru.andrew.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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

    public Person show(int person_id) {
        String sql = "SELECT person_id, full_name, birthday FROM Person WHERE person_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{person_id}, (rs, rowNum) -> {
            Person person = new Person();
            person.setPerson_id(rs.getInt("person_id"));
            person.setFull_name(rs.getString("full_name"));
            person.setBirthday(rs.getInt("birthday"));
            return person;
        });
    }

    public Optional<Person> show(String name){
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?",
                new Object[] {name}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
       }

    public Integer save(Person person) {
        String sql = "INSERT INTO Person (full_name, birthday) VALUES (?, ?) RETURNING person_id";
        Integer generatedId = jdbcTemplate.queryForObject(sql, new Object[]{person.getFull_name(), person.getBirthday()}, Integer.class);
        return generatedId != null ? generatedId : 0;
    }

    public void update(int person_id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, birthday=? WHERE person_id=?",
                updatePerson.getFull_name(), updatePerson.getBirthday(), person_id);
    }

    public void delete(int person_id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", person_id);
    }
}