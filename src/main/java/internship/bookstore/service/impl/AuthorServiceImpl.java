package internship.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import internship.bookstore.entities.Author;
import internship.bookstore.repository.AuthorRepository;
import internship.bookstore.service.AuthorService;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	AuthorRepository authorRepository;

	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.getAllAuthors();
	}

	@Override
	public Author getAuthorById(Long id) {
		return authorRepository.getAuthorById(id);
	}

	@Override
	public boolean addAuthor(Author author) {
		if (authorRepository.getAuthorByFirstNameAndLastName(author.getFirstname(), author.getLastname())
				.getId() == null) {
			return authorRepository.addAuthor(author);
		} else {
			return false;
		}
	}

	@Override
	public boolean editAuthor(Author author) {
		return authorRepository.editAuthor(author);
	}

	@Override
	public boolean deleteAuthor(Author author) {
		return authorRepository.deleteAuthor(author);
	}

}
