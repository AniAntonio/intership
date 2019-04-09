package internship.bookstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	@Autowired
	BookService bookService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView home() {

		return new ModelAndView("login.html");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute(name = "User") User user, HttpServletRequest request) {
		user = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (user.getUsername() == null) {
			System.out.println("user not found");
			ModelAndView mv = new ModelAndView("redirect:/");
			return mv;
		} else {
			request.getSession().setAttribute("user", user);
			return new ModelAndView("redirect:/adminHome");
		}
	}

	@GetMapping("/adminHome")
	public ModelAndView adminHome(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin/adminHome.html");
		User user = (User) request.getSession().getAttribute("user");
		mv.addObject("user", user);
		return mv;
	}

}
