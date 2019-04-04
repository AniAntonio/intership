package internship.bookstore.repository;

import java.util.List;

import internship.bookstore.entities.Book;

public interface BookRepository {

	List<Book> getAllBooksByIdUser(Long iduser);

	Book getBookByTitle(String title,Long iduser);

	boolean addBook(Book book);

	boolean editBook(Book book);

	boolean deleteBook(Book book);

}
