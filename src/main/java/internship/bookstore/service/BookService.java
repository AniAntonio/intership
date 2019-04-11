package internship.bookstore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import internship.bookstore.dto.BookDto;
import internship.bookstore.entities.Book;

public interface BookService {
	List<BookDto> getAllBookBySearchTitle(String searchparam);

	List<BookDto> getAllBookByPageNumber(int pageNumber);

	BookDto getBookByTitle(String searchedTitle);

	BookDto getBookByIsbn(Long isbn);

	boolean addBook(BookDto book);

	boolean editBook(BookDto book);

	boolean deleteBook(BookDto book);

}
