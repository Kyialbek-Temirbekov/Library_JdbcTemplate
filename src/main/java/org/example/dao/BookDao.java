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
public class BookDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book",
                new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (title,author,year) VALUES(?,?,?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }
    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, year=? WHERE id=?",
                book.getTitle(), book.getAuthor(), book.getYear(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT * FROM person JOIN book ON book.personId = person.id " +
                "WHERE book.id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void assign(int bookId, int personId) {
        jdbcTemplate.update("UPDATE book SET personId=? WHERE id=?",personId,bookId);
    }
    public void release(int id) {
        jdbcTemplate.update("UPDATE book SET personId = NULL WHERE id=?",id);
    }
}
