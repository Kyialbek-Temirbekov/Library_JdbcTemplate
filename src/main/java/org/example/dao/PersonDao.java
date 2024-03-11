package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id) {
        return  jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE full_name=?",new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (full_name, year) VALUES(?,?)",
                person.getFull_name(), person.getYear());
    }
    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET full_name=?, year=? WHERE id=?",
                person.getFull_name(), person.getYear(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?",id);
    }
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE personId=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
