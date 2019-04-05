package internship.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import internship.bookstore.entities.Book;
import internship.bookstore.repository.BookRepository;
import internship.bookstore.service.BookService;
import intership.bookstore.converters.BookConverter;
import intership.bookstore.dto.BookDto;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

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
			return bookRepository.addBook(BookConverter.toBookEntity(bookDto));
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
