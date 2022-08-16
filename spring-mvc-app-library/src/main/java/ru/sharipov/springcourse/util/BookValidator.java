package ru.sharipov.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.sharipov.springcourse.dao.BookDAO;
import ru.sharipov.springcourse.models.Book;

@Component
public class BookValidator implements Validator {

	private final BookDAO bookDAO;
	
	@Autowired
	public BookValidator(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Book book = (Book) target;
		if (bookDAO.show(book.getTitle(), book.getAuthor_name(), book.getAuthor_surname(), book.getYear_of_publishing()).isPresent()) {
			errors.rejectValue("title", "", "This book is already exist!");
			errors.rejectValue("author_name", "", "This book is already exist!");
			errors.rejectValue("author_surname", "", "This book is already exist!");
			errors.rejectValue("year_of_publising", "", "This book is already exist!");
		}
		
	}

}
