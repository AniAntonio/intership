package internship.bookstore.service;

import java.util.List;

import internship.bookstore.dto.BookDto;

public interface BookService {
	List<BookDto> getAllBooks(String searchparam);

	List<BookDto> getAllBooks();

	BookDto getBookByTitle(String title);

	BookDto getBookByIsbn(Long isbn);

	boolean addBook(BookDto book);

	boolean editBook(BookDto book);

	boolean deleteBook(BookDto book);

}
