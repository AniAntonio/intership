package internship.bookstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import internship.bookstore.dto.AuthorDto;
import internship.bookstore.entities.User;
import internship.bookstore.service.AuthorService;

@Controller
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@GetMapping("/admin/authorList")
	public ModelAndView goToBookhome() {
		ModelAndView mv = new ModelAndView("admin/authorList.html");
		mv.addObject("authors", authorService.getAllAuthors());
		return mv;
	}

	@GetMapping("/author/add")
	public ModelAndView goToAddAuthorPage(AuthorDto authorDto) {
		ModelAndView mv = new ModelAndView("admin/addAuthor.html");
		mv.addObject("author", authorDto);
		mv.addObject("addStatus", Boolean.TRUE);
		return mv;
	}

	@GetMapping("/author/edit/{id}")
	public ModelAndView goToEditAuthorPage(@PathVariable("id") Long id) {
		AuthorDto authorDto = authorService.getAuthorById(id);
		ModelAndView mv = new ModelAndView("admin/addAuthor.html");
		mv.addObject("author", authorDto);
		mv.addObject("addStatus", Boolean.FALSE);
		return mv;
	}

	@GetMapping("/author/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		authorService.deleteAuthor(authorService.getAuthorById(id));
		return new ModelAndView("redirect:/admin/authorList");

	}

	@PostMapping("/author")
	public ModelAndView addAuthor(@ModelAttribute @Valid AuthorDto authorDto, BindingResult result,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		authorDto.setUser(user);
		if (!authorService.addAuthor(authorDto)) {
			ModelAndView mv = new ModelAndView("admin/addAuthor.html");
			mv.addObject("author", authorDto);
			mv.addObject("addStatus", Boolean.TRUE);
			mv.addObject("message", "Author with this name already exists!");
			mv.addObject("alertClass", "alert-danger");
			return mv;
		}
		return new ModelAndView("redirect:/admin/authorList");
	}

	@PostMapping("/author/edit")
	public ModelAndView editAuthor(@Valid AuthorDto authorDto, BindingResult result, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		User user = (User) request.getSession().getAttribute("user");
		authorDto.setUser(user);
		if (!authorService.editAuthor(authorDto)) {
			ModelAndView mv = new ModelAndView("admin/addAuthor.html");
			mv.addObject("author", authorDto);
			mv.addObject("addStatus", Boolean.FALSE);
			mv.addObject("message", "Author with this name already exists!");
			mv.addObject("alertClass", "alert-danger");
			return mv;
		}
		return new ModelAndView("redirect:/admin/authorList");
	}
}
