package internship.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.bookstore.entities.Book;
import internship.bookstore.entities.BookReview;
import internship.bookstore.repository.BookRepository;
import internship.bookstore.repository.BookReviewRepository;
import internship.bookstore.service.BookReviewService;

@Service
@Transactional
public class BookReviewServiceImpl implements BookReviewService {

	@Autowired
	BookReviewRepository bookReviewRepository;
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<BookReview> getAllBookreviews(Long isbnBook) {
		List<BookReview> reviews = new ArrayList<>();
		reviews = bookReviewRepository.getAllBookreviews(isbnBook);
		return reviews;
	}

	@Override
	public boolean addBookReview(BookReview bookReview) {
		if (bookReviewRepository.addBookReview(bookReview)) {
			Book book = bookRepository.getBookByIsbn(bookReview.getBook().getIsbn());
			book.setRating(bookReviewRepository.calculateBookRating(bookReview.getBook().getIsbn()));
			bookRepository.editBook(book);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkIfUserHasDoneReview(Long isbnBook, Long iduser) {
		return (bookReviewRepository.checkIfUserHasDoneReview(isbnBook, iduser).getId() == null);
	}

}
