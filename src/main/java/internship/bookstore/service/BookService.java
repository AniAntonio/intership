package internship.bookstore.service;

import java.util.List;

import intership.bookstore.dto.BookDto;

public interface BookService {
	List<BookDto> getAllBooksByUserId(Long iduser);

	List<BookDto> getAllBooks();

	BookDto getBookByTitle(String title);

	BookDto getBookByIsbn(Long isbn);

	boolean addBook(BookDto book);

	boolean editBook(BookDto book);

	boolean deleteBook(BookDto book);

}
