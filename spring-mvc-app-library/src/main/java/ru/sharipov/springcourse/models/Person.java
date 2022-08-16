package ru.sharipov.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {
	
	
	@NotEmpty(message = "Name shouldn't be empty")
	@Size(min = 2, max = 30, message = "Name should be between 1 and 30 characters")
	@Pattern(regexp = "[A-Z]\\w+", message = "The first character of name should be capital")
	private String name;
	
	@NotEmpty(message = "Surname shouldn't be empty")
	@Size(min = 2, max = 30, message = "Surname should be between 1 and 30 characters")
	@Pattern(regexp = "[A-Z]\\w+", message = "The first character of surname should be capital")
	private String surname;
	
	@Size(min = 0, max = 30, message = "Patronymic should be between 1 and 30 characters")
	@Pattern(regexp = "[A-Z]*\\w*", message = "The first character of patronymic should be capital")
	private String patronymic;
	
	private int person_id;
	
	@Min(value = 1900, message = "Age should be positive")
	private int birthdate;
	
	public Person(String name, String surname, String patronymic, int birthdate) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.birthdate = birthdate;
	}

	public int getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}

	public Person() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	
}
