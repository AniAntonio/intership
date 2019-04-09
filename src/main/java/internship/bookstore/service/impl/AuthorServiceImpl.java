package internship.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import internship.bookstore.converters.AuthorConverter;
import internship.bookstore.dto.AuthorDto;
import internship.bookstore.entities.Author;
import internship.bookstore.entities.Book;
import internship.bookstore.repository.AuthorRepository;
import internship.bookstore.service.AuthorService;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	AuthorRepository authorRepository;

	@Override
	public List<AuthorDto> getAllAuthors() {
		List<AuthorDto> authors = new ArrayList<AuthorDto>();
		for (Author author : authorRepository.getAllAuthors()) {
			authors.add(AuthorConverter.toAuthorDto(author));
		}
		return authors;
	}

	@Override
	public AuthorDto getAuthorById(Long id) {

		return AuthorConverter.toAuthorDto(authorRepository.getAuthorById(id));
	}

	@Override
	public boolean addAuthor(AuthorDto authorDto) {
		if (authorRepository.getAuthorByFirstNameAndLastName(authorDto.getFirstname(), authorDto.getLastname())
				.getId() == null) {
			return authorRepository.addAuthor(AuthorConverter.toAuthorEntity(authorDto));
		} else {
			return false;
		}
	}

	@Override
	public boolean editAuthor(AuthorDto authorDto) {
		if (authorRepository.getAuthorByFirstNameAndLastName(authorDto.getFirstname(), authorDto.getLastname())
				.getId() == null) {
			return authorRepository.editAuthor(AuthorConverter.toAuthorEntity(authorDto));
		} else if (authorRepository.getAuthorByFirstNameAndLastName(authorDto.getFirstname(), authorDto.getLastname())
				.getId() == authorDto.getId()) {
			return authorRepository.editAuthor(AuthorConverter.toAuthorEntity(authorDto));
		} else {
			// you are giving firstname and last name of an author that already exists
			return false;
		}
	}

	@Override
	public boolean deleteAuthor(AuthorDto authorDto) {
			if(authorRepository.getAuthorById(authorDto.getId()).getBooks().isEmpty()) {
			return authorRepository.deleteAuthor(AuthorConverter.toAuthorEntity(authorDto));
			}else {
				System.out.println("Cant delete this author which has written book");
				return false;
			}
	}

}
