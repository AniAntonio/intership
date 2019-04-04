package internship.bookstore.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import internship.bookstore.entities.User;
import internship.bookstore.service.AuthorService;
import internship.bookstore.service.BookService;
import internship.bookstore.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	AuthorService authorService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("login.html"); // this one works
		return mv;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute(name = "User") User user) {
		user = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (user.getUsername() == null) {
			System.out.println("user not found");
			ModelAndView mv = new ModelAndView("login.html");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("authorHome.html"); // this one works
			mv.addObject("authors", authorService.getAllAuthors());
			return mv;
		}
	}

}
