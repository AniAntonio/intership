package internship.bookstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import internship.bookstore.entities.User;
import internship.bookstore.service.UserService;

@Controller
public class UserController {
	
	private static final String REGISTER_URL= "register.html";
	
	@Autowired
	UserService userService;

	@GetMapping(value = { "/" })
	public ModelAndView home() {

		return new ModelAndView("login.html");
	}
	
	@GetMapping(value = { "/errorPage" })
	public ModelAndView errorPage() {

		return new ModelAndView("errorPage.html");
	}

	@GetMapping(value = "/login")
	public ModelAndView login(@ModelAttribute(name = "User") User user, HttpServletRequest request) {
		user = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (user.getUsername() == null) {
			ModelAndView mv = new ModelAndView("login.html");
			mv.addObject("message", "Wrong username or password!");
			mv.addObject("alertClass", "alert-danger");
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

	@GetMapping("/register")
	public ModelAndView goToRegisterPage(User user) {
		ModelAndView mv = new ModelAndView(REGISTER_URL);
		mv.addObject("user", user);
		return mv;
	}

	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute @Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView(REGISTER_URL);
		}
		if (!userService.addUser(user)) {
			ModelAndView mv = new ModelAndView(REGISTER_URL);
			mv.addObject("message", "This username already exists!");
			mv.addObject("alertClass", "alert-danger");
			return mv;
		}
		return new ModelAndView("redirect:/");
	}

	@GetMapping("/logout")
	public ModelAndView goToLoginPage(User user, HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/");
	}

}
