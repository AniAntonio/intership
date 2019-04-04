package internship.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import internship.bookstore.entities.Book;
import internship.bookstore.repository.BookRepository;
import internship.bookstore.service.BookService;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> getAllBooksByUserId(Long iduser) {

		return bookRepository.getAllBooksByIdUser(iduser);
	}

	@Override
	public Book getBookByTitle(String title, Long iduser) {

		return bookRepository.getBookByTitle(title, iduser);
	}

	@Override
	public boolean addBook(Book book) {

		return bookRepository.addBook(book);
	}

	@Override
	public boolean editBook(Book book) {

		return bookRepository.editBook(book);
	}

	@Override
	public boolean deleteBook(Book book) {

		return bookRepository.deleteBook(book);
	}

}
