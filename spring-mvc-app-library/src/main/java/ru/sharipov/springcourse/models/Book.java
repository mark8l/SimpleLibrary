package ru.sharipov.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {
	
	
	@NotEmpty(message = "Title shouldn't be empty")
	@Size(min = 2, max = 100, message = "Title should be between 1 and 50 characters")
	@Pattern(regexp = "[A-Z][\\d\\D.]+", message = "The first character of title should be capital")
	private String title;
	
	@NotEmpty(message = "Name shouldn't be empty")
	@Size(min = 2, max = 30, message = "Name should be between 1 and 30 characters")
	@Pattern(regexp = "[A-Z]\\w+", message = "The first character of name should be capital")
	private String author_name;
	
	@NotEmpty(message = "Surname shouldn't be empty")
	@Size(min = 2, max = 30, message = "Surname should be between 1 and 30 characters")
	@Pattern(regexp = "[A-Z]\\w+", message = "The first character of surname should be capital")
	private String author_surname;
	
	@Min(value = 0, message = "Year of publishing should be positive")
	@Max(value = 2022, message = "Year of publishing should be correct")
	private int year_of_publishing;
	
	private Integer person_id;

	private int book_id;

	public Book() {
	}
	
	public Book(String title, String author_name, String author_surname, int year_of_publishing) {
		this.title = title;
		this.author_name = author_name;
		this.author_surname = author_surname;
		this.year_of_publishing = year_of_publishing;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getAuthor_surname() {
		return author_surname;
	}

	public void setAuthor_surname(String author_surname) {
		this.author_surname = author_surname;
	}

	public int getYear_of_publishing() {
		return year_of_publishing;
	}

	public void setYear_of_publishing(int year_of_publishing) {
		this.year_of_publishing = year_of_publishing;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int id) {
		this.book_id = id;
	}

	public Integer getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}

}
