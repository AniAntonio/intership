package internship.bookstore.repository;

import java.util.List;

import internship.bookstore.entities.Author;
import internship.bookstore.entities.Book;

public interface BookRepository {

	List<Book> getAllBookBySearch(String searchedTitle,Author author,int pageNumber);

	int countBooks(String searchedTitle,Author author);

	Book getBookByTitle(String title);

	Book getBookByIsbn(Long isbn);

	boolean addBook(Book book);

	boolean editBook(Book book);

	boolean deleteBook(Book book);

}
