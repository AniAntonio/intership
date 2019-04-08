package internship.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import internship.bookstore.entities.Author;
import internship.bookstore.entities.Book;
import internship.bookstore.entities.BookAuthor;
import internship.bookstore.repository.AuthorRepository;
import internship.bookstore.repository.BookAuthorRepository;
import internship.bookstore.repository.BookRepository;
import internship.bookstore.service.BookService;
import intership.bookstore.converters.BookConverter;
import intership.bookstore.dto.BookDto;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookAuthorRepository bookAuthorRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Override
	public List<BookDto> getAllBooksByUserId(Long iduser) {
		List<BookDto> books = new ArrayList<BookDto>();
		for (Book book : bookRepository.getAllBooksByIdUser(iduser)) {
			books.add(BookConverter.toBookDto(book));
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
			bookRepository.addBook(BookConverter.toBookEntity(bookDto));
			Book book = bookRepository.getBookByTitle(bookDto.getTitle());
			for (Long id : bookDto.getIdAuthors()) {
				BookAuthor bookAuthor = new BookAuthor();
				Author author = new Author();
				author = authorRepository.getAuthorById(id);
				bookAuthor.setBook(book);
				bookAuthor.setAuthor(author);
				bookAuthorRepository.addBookAuthor(bookAuthor);
			}
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
			return bookRepository.editBook(book);
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteBook(BookDto bookDto) {
		Book book = bookRepository.getBookByIsbn(bookDto.getIsbn());
		for (BookAuthor bookAuthor : book.getBookAuthors()) {
			bookAuthorRepository.deleteBookAuthors(bookAuthor);
		}
		return bookRepository.deleteBook(BookConverter.toBookEntity(bookDto));
	}

	@Override
	public BookDto getBookByIsbn(Long isbn) {
		return BookConverter.toBookDto(bookRepository.getBookByIsbn(isbn));
	}

	@Override
	public List<BookDto> getAllBooks() {

		List<BookDto> books = new ArrayList<BookDto>();
		for (Book book : bookRepository.getAllBooks()) {
			books.add(BookConverter.toBookDto(book));
		}
		return books;
	}

}
