package internship.bookstore.service;

import java.util.List;

import internship.bookstore.entities.Author;

public interface AuthorService {

	List<Author> getAllAuthors();

	Author getAuthorById(Long id);

	boolean addAuthor(Author author);

	boolean editAuthor(Author author);

	boolean deleteAuthor(Author author);

}
