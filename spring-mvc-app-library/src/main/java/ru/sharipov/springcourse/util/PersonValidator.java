package ru.sharipov.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.sharipov.springcourse.dao.PersonDAO;
import ru.sharipov.springcourse.models.Person;

@Component
public class PersonValidator implements Validator {

	private final PersonDAO personDAO;
	
	@Autowired
	public PersonValidator(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Person person = (Person) target;
		
		if (person.getPatronymic() != null) {
			if (personDAO.show(person.getName(), person.getSurname(), person.getPatronymic(), person.getBirthdate()).isPresent()) {
				errors.rejectValue("name", "", "Person with this name, surname, patronymic and birthdate is already exist!");
				errors.rejectValue("surname", "", "Person with this name, surname, patronymic and birthdate is already exist!");
				errors.rejectValue("patronymic", "", "Person with this name, surname, patronymic and birthdate is already exist!");
				errors.rejectValue("birthdate", "", "Person with this name, surname, patronymic and birthdate is already exist!");
			}
		}
		else {
			if (personDAO.show(person.getName(), person.getSurname(), person.getBirthdate()).isPresent()) {
				errors.rejectValue("name", "", "Person with this name, surname and birthdate is already exist!");
				errors.rejectValue("surname", "", "Person with this name, surname and birthdate is already exist!");
				errors.rejectValue("birthdate", "", "Person with this name, surname and birthdate is already exist!");
			}
		}
	}

}
