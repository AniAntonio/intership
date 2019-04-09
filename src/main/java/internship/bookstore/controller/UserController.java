package internship.bookstore.controller;

import javax.servlet.http.HttpServletRequest;
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
		if (result.hasErrors() || !userService.addUser(user)) {
			return new ModelAndView("redirect:/register");
		}
		return new ModelAndView("redirect:/login");
	}

	@GetMapping("/logout")
	public ModelAndView goToLoginPage(User user, HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/login");
	}

}
