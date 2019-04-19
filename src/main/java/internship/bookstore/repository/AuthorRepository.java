package internship.bookstore.repository;

import java.util.List;

import internship.bookstore.entities.Author;

public interface AuthorRepository {

	List<Author> getAllAuthors();

	List<Author> getAuthorsByIdList(List<Long> ids);

	Author getAuthorByFirstNameAndLastName(String firstname, String lastname);

	Author getAuthorById(Long id);

	boolean addAuthor(Author author);

	boolean editAuthor(Author author);

	boolean deleteAuthor(Author author);

}
