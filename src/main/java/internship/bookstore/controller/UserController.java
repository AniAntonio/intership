package internship.bookstore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import internship.bookstore.entities.User;
import internship.bookstore.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value={"/users"}, method = RequestMethod.GET)
	public ModelAndView usersTest() {
	  List<User> users = userService.getAllUsers();
	  System.out.println(users.size());
		ModelAndView mv = new ModelAndView("home.html");
		mv.addObject("someData", users.size());
		return mv;
	}
	
	@RequestMapping(value={"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
      System.out.println("home2");
        ModelAndView mv = new ModelAndView("home.html"); //this one works
        mv.addObject("someData", "some data");
        return mv;
    }
	
	@RequestMapping(value={"/home3"}, method = RequestMethod.GET)
    public ModelAndView home3() {
      System.out.println("home3");
        ModelAndView mv = new ModelAndView("/home.html");
        mv.addObject("someData", "some data");
        return mv;
    }
	
	@RequestMapping(value={"/home4"}, method = RequestMethod.GET)
    public String home4() {
      System.out.println("home4");
        return "home";
    }
}
