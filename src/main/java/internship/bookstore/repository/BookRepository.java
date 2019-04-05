package internship.bookstore.repository;

import java.util.List;

import internship.bookstore.entities.Book;

public interface BookRepository {

	List<Book> getAllBooksByIdUser(Long iduser);
	
	List<Book> getAllBooks();

	Book getBookByTitle(String title);

	Book getBookByIsbn(Long isbn);

	boolean addBook(Book book);

	boolean editBook(Book book);

	boolean deleteBook(Book book);

}
