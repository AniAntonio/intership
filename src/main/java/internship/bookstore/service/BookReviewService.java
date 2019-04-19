package internship.bookstore.service;

import java.util.List;

import internship.bookstore.entities.BookReview;

public interface BookReviewService {
	
	List<BookReview> getAllBookreviews(Long isbnBook);

	boolean addBookReview(BookReview bookReview);

	boolean checkIfUserHasDoneReview(Long isbnBook, Long iduser);

}
