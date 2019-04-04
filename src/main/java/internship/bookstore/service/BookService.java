package internship.bookstore.service;

import java.util.List;

import internship.bookstore.entities.Book;

public interface BookService {
	List<Book> getAllBooksByUserId(Long iduser);

	Book getBookByTitle(String title, Long iduser);

	boolean addBook(Book book);

	boolean editBook(Book book);

	boolean deleteBook(Book book);

}
