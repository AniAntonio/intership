package internship.bookstore.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import internship.bookstore.converters.BookConverter;
import internship.bookstore.dto.BookDto;
import internship.bookstore.dto.BookRequestDto;
import internship.bookstore.entities.Author;
import internship.bookstore.entities.Book;
import internship.bookstore.repository.AuthorRepository;
import internship.bookstore.repository.BookRepository;
import internship.bookstore.service.BookService;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Override
	public List<BookDto> getAllBookBySearchTitle(BookRequestDto request) {

		List<BookDto> books = new ArrayList<BookDto>();
		if (request.getIdAuthor() != 0) {
			Author author = authorRepository.getAuthorById((long) request.getIdAuthor());
			for (Book book : bookRepository.getAllBookBySearchTitle(request.getSearchedTitle(), author,
					request.getPageNumber())) {
				books.add(BookConverter.toBookDto(book));
			}
		} else {
			for (Book book : bookRepository.getAllBookBySearchTitle(request.getSearchedTitle(), null,
					request.getPageNumber())) {
				books.add(BookConverter.toBookDto(book));
			}
		}
		return books;
	}

	@Override
	public BookDto getBookByTitle(String title) {

		return BookConverter.toBookDto(bookRepository.getBookByTitle(title));
	}

	@Override
	public boolean addBook(BookDto bookDto) {

		if (bookRepository.getBookByTitle(bookDto.getTitle()).getIsbn() == null) {
			Set<Author> authors = new HashSet<>();
			for (Long id : bookDto.getIdAuthors()) {
				Author author = new Author();
				author = authorRepository.getAuthorById(id);
				authors.add(author);
			}
			bookDto.setAuthors(authors);
			bookRepository.addBook(BookConverter.toBookEntity(bookDto));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean editBook(BookDto bookDto) {
		if (bookRepository.getBookByTitle(bookDto.getTitle()).getIsbn() == null
				|| bookDto.getIsbn() == bookRepository.getBookByTitle(bookDto.getTitle()).getIsbn()) {
			Book book = bookRepository.getBookByIsbn(bookDto.getIsbn());
			book.setDescription(bookDto.getDescription());
			book.setPublishingdate(bookDto.getPublishingdate());
			book.setTitle(bookDto.getTitle());
			book.setIsbn(bookDto.getIsbn());
			Set<Author> authors = new HashSet<>();
			for (Long id : bookDto.getIdAuthors()) {
				Author author = new Author();
				author = authorRepository.getAuthorById(id);
				authors.add(author);
			}
			book.setAuthors(authors);
			return bookRepository.editBook(book);
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteBook(BookDto bookDto) {
		return bookRepository.deleteBook(BookConverter.toBookEntity(bookDto));
	}

	@Override
	public BookDto getBookByIsbn(Long isbn) {
		return BookConverter.toBookDto(bookRepository.getBookByIsbn(isbn));
	}

	@Override
	public int countBooks(BookRequestDto request) {
		if (request.getIdAuthor() != 0) {
			Author author = authorRepository.getAuthorById((long) request.getIdAuthor());
			return bookRepository.countBooks(request.getSearchedTitle(), author);
		} else {
			return bookRepository.countBooks(request.getSearchedTitle(), null);
		}
	}
}
