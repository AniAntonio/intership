package internship.bookstore.service;

import java.util.List;

import internship.bookstore.entities.Author;
import intership.bookstore.dto.AuthorDto;

public interface AuthorService {

	List<AuthorDto> getAllAuthors();

	AuthorDto getAuthorById(Long id);

	boolean addAuthor(AuthorDto authorDto);

	boolean editAuthor(AuthorDto authorDto);

	boolean deleteAuthor(AuthorDto authorDto);

}
