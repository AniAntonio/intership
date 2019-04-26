package internship.bookstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	private static final String ADD_AUTHOR_URL = "admin/addAuthor.html";
	private static final String REDIRECT_TO_AUTHOR_LIST = "redirect:/admin/authorList";
	private static final String ADD_STATUS = "addStatus";
	private static final String AUTHOR = "author";
	private static final String MESSAGE = "message";
	private static final String ALERT_CLASS = "alertClass";
	private static final String ALERT_SUCCESS = "alert alert-success";

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
		ModelAndView mv = new ModelAndView(ADD_AUTHOR_URL);
		mv.addObject(AUTHOR, authorDto);
		mv.addObject(ADD_STATUS, Boolean.TRUE);
		return mv;
	}

	@GetMapping("/author/edit/{id}")
	public ModelAndView goToEditAuthorPage(@PathVariable("id") Long id) {
		AuthorDto authorDto = authorService.getAuthorById(id);
		ModelAndView mv = new ModelAndView(ADD_AUTHOR_URL);
		mv.addObject(AUTHOR, authorDto);
		mv.addObject(ADD_STATUS, Boolean.FALSE);
		return mv;
	}

	@GetMapping("/author/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes redirectAttrs) {
		if (authorService.deleteAuthor(authorService.getAuthorById(id))) {
			redirectAttrs.addFlashAttribute(MESSAGE, "Author deleted successfully!");
			redirectAttrs.addFlashAttribute(ALERT_CLASS, ALERT_SUCCESS);

		} else {
			redirectAttrs.addFlashAttribute(MESSAGE, "Author can't be deleted becouse he has books!!");
			redirectAttrs.addFlashAttribute(ALERT_CLASS, "alert alert-danger");

		}
		return new ModelAndView(REDIRECT_TO_AUTHOR_LIST);
	}

	@PostMapping("/author")
	public ModelAndView saveAuthor(@ModelAttribute @Valid AuthorDto authorDto, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		User user = (User) request.getSession().getAttribute("user");
		authorDto.setUser(user);
		ModelAndView mv = new ModelAndView(ADD_AUTHOR_URL);
		mv.addObject(AUTHOR, authorDto);
		mv.addObject(MESSAGE, "Author with this name already exists!");
		mv.addObject(ALERT_CLASS, "alert-danger");
		if (authorDto.getId() == null) {
			if (!authorService.addAuthor(authorDto)) {
				mv.addObject(ADD_STATUS, Boolean.TRUE);
				return mv;
			}
			redirectAttrs.addFlashAttribute(MESSAGE, "Author added successfully!");
			redirectAttrs.addFlashAttribute(ALERT_CLASS, ALERT_SUCCESS);
		} else if (!authorService.editAuthor(authorDto)) {
			mv.addObject(ADD_STATUS, Boolean.FALSE);
			return mv;
		} else {
			redirectAttrs.addFlashAttribute(MESSAGE, "Author edited successfully!");
			redirectAttrs.addFlashAttribute(ALERT_CLASS, ALERT_SUCCESS);
		}
		return new ModelAndView(REDIRECT_TO_AUTHOR_LIST);
	}
}
