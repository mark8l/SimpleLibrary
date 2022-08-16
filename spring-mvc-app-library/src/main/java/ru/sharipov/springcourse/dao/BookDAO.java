package ru.sharipov.springcourse.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.sharipov.springcourse.models.Book;

@Component
public class BookDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Book> index() {
		return jdbcTemplate.query("SELECT * FROM u520564_devmysite.Book", new BeanPropertyRowMapper<>(Book.class));
	}
	
	public void save(Book book) {
		jdbcTemplate.update("INSERT INTO u520564_devmysite.Book (title, author_name, author_surname, year_of_publishing) VALUES (?, ?, ?, ?)",
							book.getTitle(), book.getAuthor_name(), book.getAuthor_surname(), book.getYear_of_publishing());
	}
	
	public Optional<Book> show(String title, String author_name, String author_surname, int year_of_publishing) {
		return jdbcTemplate.query("SELECT * FROM u520564_devmysite.Book WHERE title=? AND author_name=? AND author_surname=? AND year_of_publishing=?",
				new BeanPropertyRowMapper<>(Book.class), new Object[] { title, author_name, author_surname, year_of_publishing }).stream().findAny();
	}
	
	public Book show(int id) {
		return jdbcTemplate.query("SELECT * FROM u520564_devmysite.Book WHERE book_id=?",
						    new BeanPropertyRowMapper<>(Book.class), new Object[] { id })
						   .stream().findAny().orElse(null);
	}
	
	
	public void update(Book book, int id) {
		jdbcTemplate.update("UPDATE u520564_devmysite.Book SET title=?, author_name=?, author_surname=?, year_of_publishing=? WHERE book_id=?"
							,book.getTitle(), book.getAuthor_name(), book.getAuthor_surname(), book.getYear_of_publishing(), id);
	}
	
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM u520564_devmysite.Book WHERE book_id=?", id);
	}
	
	public void setPersonToBook(int person_id, int book_id) {
		jdbcTemplate.update("UPDATE u520564_devmysite.Book SET person_id=? WHERE book_id=?", person_id, book_id);
	}
	
	public void setBookFree(int book_id) {
		jdbcTemplate.update("UPDATE u520564_devmysite.Book SET person_id=? WHERE book_id=?", null, book_id);
	}
	
	public List<Book> getBooksByPersonId(int id) {
		return jdbcTemplate.query("SELECT * FROM u520564_devmysite.Book WHERE person_id=?", new BeanPropertyRowMapper<Book>(Book.class), new Object[] {id});
	}

}
