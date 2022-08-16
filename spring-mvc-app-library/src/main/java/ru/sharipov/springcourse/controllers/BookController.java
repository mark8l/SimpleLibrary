package ru.sharipov.springcourse.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.sharipov.springcourse.dao.BookDAO;
import ru.sharipov.springcourse.dao.PersonDAO;
import ru.sharipov.springcourse.models.Book;
import ru.sharipov.springcourse.models.Person;
import ru.sharipov.springcourse.util.BookValidator;

@Controller
@RequestMapping("/book")
public class BookController {
	
	private BookDAO bookDAO;
	private BookValidator bookValidator;
	private PersonDAO personDAO;
	
	@Autowired
	public BookController(BookDAO bookDAO, BookValidator bookValidator, PersonDAO personDAO) {
		this.bookDAO = bookDAO;
		this.bookValidator = bookValidator;
		this.personDAO = personDAO;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("books", bookDAO.index());
		return "book/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
		
		Book book = bookDAO.show(id);
		model.addAttribute("book", book);
		if (book.getPerson_id() != null) {
			model.addAttribute("person", personDAO.show(book.getPerson_id()));
		}
		else {
			model.addAttribute("people", personDAO.index());
		}
		model.addAttribute("book", book);
		return "book/show";
	}
	
	@GetMapping("/new")
	public String newBook(@ModelAttribute("book") Book book) {
		return "book/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		
		bookValidator.validate(book, bindingResult);
		if (bindingResult.hasErrors()) {
			return "book/new";
		}
		bookDAO.save(book);
		return "redirect:/book";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("book", bookDAO.show(id));
		return "book/edit";
	}
	
	@PatchMapping("/{id}")
	public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
		
		if (bindingResult.hasErrors()) {
			return "book/edit";
		}
		bookDAO.update(book, id);
		return "redirect:/book";
		
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		
		bookDAO.delete(id);
		return "redirect:/book";
		
	}
	
	@PatchMapping("/take/{id}")
	public String takeBook(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
		bookDAO.setPersonToBook(person.getPerson_id(), id);
		return "redirect:/book";
		
	}
	
	@PatchMapping("/free")
	public String freeBook(@RequestParam("book_id") int book_id) {
		bookDAO.setBookFree(book_id);
		return "redirect:/book";
		
	}
	
}
