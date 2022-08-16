package ru.sharipov.springcourse.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.sharipov.springcourse.models.Person;

@Component
public class PersonDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Person> index() {
		return jdbcTemplate.query("SELECT * FROM u520564_devmysite.Person", new BeanPropertyRowMapper<>(Person.class));
	}
	
	public Optional<Person> show(String name, String surname, int birthdate) {
		return jdbcTemplate.query("SELECT * FROM u520564_devmysite.Person WHERE name=? AND surname=? AND birthdate=?",
				new BeanPropertyRowMapper<>(Person.class), new Object[] { name, surname, birthdate }).stream().findAny();
	}
	
	public Optional<Person> show(String name, String surname, String patronymic, int birthdate) {
		return jdbcTemplate.query("SELECT * FROM u520564_devmysite.Person WHERE name=? AND surname=? AND patronymic=? AND birthdate=?",
				new BeanPropertyRowMapper<>(Person.class), new Object[] { name, surname, patronymic, birthdate }).stream().findAny();
	}

	public Person show(int id) {

		return jdbcTemplate.query("SELECT * FROM u520564_devmysite.Person WHERE person_id=?",
				new BeanPropertyRowMapper<>(Person.class), new Object[] { id }).stream().findAny().orElse(null);
	}

	public void save(Person person) {
		jdbcTemplate.update("INSERT INTO u520564_devmysite.Person (name, surname, patronymic, birthdate) VALUES (?, ?, ?, ?)",
				person.getName(), person.getSurname(), person.getPatronymic(), person.getBirthdate());
	}

	public void update(int id, Person person) {
		jdbcTemplate.update("UPDATE u520564_devmysite.Person SET name=?, surname=?, patronymic=?, birthdate=? WHERE person_id=?"
				, person.getName(), person.getSurname(), person.getPatronymic(), person.getBirthdate(), id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM u520564_devmysite.Person WHERE person_id=?", id);
	}
	
	public Optional<Person> getBookOwner(int id) {
		return jdbcTemplate.query("SELECT u520564_devmysite.Person.* FROM u520564_devmysite.Person INNER JOIN u520564_devmysite.Book ON u520564_devmysite.Person.person_id=u520564_devmysite.Book.person_id WHERE u520564_devmysite.Book.person_id=?"
									,new BeanPropertyRowMapper<>(Person.class), new Object[] {id}).stream().findAny();
	}
	
}
