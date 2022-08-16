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

import ru.sharipov.springcourse.dao.BookDAO;
import ru.sharipov.springcourse.dao.PersonDAO;
import ru.sharipov.springcourse.models.Person;
import ru.sharipov.springcourse.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PersonController {

	private PersonDAO personDAO;
	private PersonValidator personValidator;
	private BookDAO bookDAO;

	@Autowired
	public PersonController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
		this.personDAO = personDAO;
		this.personValidator = personValidator;
		this.bookDAO = bookDAO;
	}

	@GetMapping()
	public String index(Model model) {

		model.addAttribute("people", personDAO.index());
		return "people/index";

	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", personDAO.show(id));
		
		if (personDAO.getBookOwner(id).isPresent()) {
			
			model.addAttribute("books", bookDAO.getBooksByPersonId(id));
			model.addAttribute("take", true);
		}
		else {
			model.addAttribute("take", false);
		}
		return "people/show";
	}

	@GetMapping("/new")
	public String newPerson(@ModelAttribute("person") Person person) {
		return "people/new";
	}

	@PostMapping()
	public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

		personValidator.validate(person, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "people/new";
		}

		personDAO.save(person);
		return "redirect:/people";

	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("person", personDAO.show(id));
		return "people/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
			@PathVariable("id") int id) {

		
		if (bindingResult.hasErrors()) {
			return "people/edit";
		}
		personDAO.update(id, person);
		return "redirect:/people";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		personDAO.delete(id);
		return "redirect:/people";
	}
}
