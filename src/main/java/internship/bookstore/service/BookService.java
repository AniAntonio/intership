package internship.bookstore.service;

import java.util.List;

import internship.bookstore.dto.BookDto;
import internship.bookstore.dto.BookRequestDto;

public interface BookService {
	List<BookDto> getAllBookBySearchTitle(BookRequestDto request);

	int countBooks(BookRequestDto request);

	BookDto getBookByTitle(String searchedTitle);

	BookDto getBookByIsbn(Long isbn);

	boolean addBook(BookDto book);

	boolean editBook(BookDto book);

	boolean deleteBook(BookDto book);

}
