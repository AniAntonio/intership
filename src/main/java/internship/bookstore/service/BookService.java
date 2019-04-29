package internship.bookstore.service;

import java.util.List;

import internship.bookstore.dto.BookDto;
import internship.bookstore.dto.BookRequestDto;

public interface BookService {
	List<BookDto> getAllBooks(BookRequestDto request);

	int countBooks(BookRequestDto request);

	BookDto getBookByTitle(String searchedTitle);

	BookDto getBookByIsbn(Long isbn);

	boolean saveBook(BookDto book);

	boolean deleteBook(BookDto book);

}
