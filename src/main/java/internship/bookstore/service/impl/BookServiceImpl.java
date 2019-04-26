package internship.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;
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
import internship.bookstore.repository.BookReviewRepository;
import internship.bookstore.service.BookService;
import internship.bookstore.service.CustomException;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookReviewRepository bookReviewRepository;

	@Override
	public List<BookDto> getAllBooksBySearch(BookRequestDto request) {
		List<BookDto> books = new ArrayList<>();
		if (request.getIdAuthor() != 0) {
			Author author = authorRepository.getAuthorById((long) request.getIdAuthor());
			for (Book book : bookRepository.getAllBookBySearch(request.getSearchedTitle(), author,
					request.getPageNumber())) {
				books.add(BookConverter.toBookDto(book));
			}
		} else {
			for (Book book : bookRepository.getAllBookBySearch(request.getSearchedTitle(), null,
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
	public boolean saveBook(BookDto bookDto) {
		Long bookId = bookRepository.getBookByTitle(bookDto.getTitle()).getIsbn();
		if (bookId == null || bookId.equals(bookDto.getIsbn())) {
			List<Author> authors = authorRepository.getAuthorsByIdList(bookDto.getIdAuthors());
			bookDto.setAuthors(authors);
			return bookRepository.saveBook(BookConverter.toBookEntity(bookDto));
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteBook(BookDto bookDto) {
		bookReviewRepository.deleteReviews(bookDto.getIsbn());
		return bookRepository.deleteBook(BookConverter.toBookEntity(bookDto));
	}

	@Override
	public BookDto getBookByIsbn(Long isbn) {
		try {
			return BookConverter.toBookDto(bookRepository.getBookByIsbn(isbn));
		} catch (Exception e) {
			throw new CustomException("Book with this id not found! You need to login again!");
		}
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
