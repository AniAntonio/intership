package internship.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import internship.bookstore.converters.AuthorConverter;
import internship.bookstore.dto.AuthorDto;
import internship.bookstore.entities.Author;
import internship.bookstore.repository.AuthorRepository;
import internship.bookstore.service.AuthorService;
import internship.bookstore.service.CustomException;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	AuthorRepository authorRepository;

	@Override
	public List<AuthorDto> getAllAuthors() {
		List<AuthorDto> authors = new ArrayList<>();
		for (Author author : authorRepository.getAllAuthors()) {
			authors.add(AuthorConverter.toAuthorDto(author));
		}
		return authors;
	}

	@Override
	public AuthorDto getAuthorById(Long id) {
		if (AuthorConverter.toAuthorDto(authorRepository.getAuthorById(id)).getFirstname() == null) {
			throw new CustomException("Wrong id of the author on edit author.Please login again!");
		} else {
			return AuthorConverter.toAuthorDto(authorRepository.getAuthorById(id));
		}
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
				.getId().equals(authorDto.getId())) {
			return authorRepository.editAuthor(AuthorConverter.toAuthorEntity(authorDto));
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteAuthor(AuthorDto authorDto) {
		if (authorRepository.getAuthorById(authorDto.getId()).getBooks().isEmpty()) {
			return authorRepository.deleteAuthor(AuthorConverter.toAuthorEntity(authorDto));
		} else {
			return false;
		}
	}

}
