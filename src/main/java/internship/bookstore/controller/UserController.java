package internship.bookstore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import internship.bookstore.entities.Role;
import internship.bookstore.entities.User;
import internship.bookstore.service.BookService;
import internship.bookstore.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;

	/*
	 * @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET) public
	 * ModelAndView home() { User user =
	 * userService.getUserByUsernameAndPassword("ani", "123"); List<User> users =
	 * userService.getAllUsers(); System.out.println(user.getEmail()); ModelAndView
	 * mv = new ModelAndView("login.html"); // this one works
	 * mv.addObject("someData", user); return mv; }
	 */

}
