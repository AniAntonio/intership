package internship.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import internship.bookstore.entities.Role;
import internship.bookstore.entities.User;
import internship.bookstore.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/register")
	public ModelAndView goToRegisterPage(User user) {
		ModelAndView mv = new ModelAndView("register.html");
		mv.addObject("user", user);
		return mv;
	}

	@PostMapping("/signup")
	public ModelAndView register(@Valid User user, BindingResult result) {
	
		userService.addUser(user);
		ModelAndView mv = new ModelAndView("login.html");
		mv.addObject("user", user);
		return mv;
	}

}
