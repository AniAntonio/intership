package internship.bookstore.repository;

import java.util.List;

import internship.bookstore.entities.BookReview;

public interface BookReviewRepository {

	List<BookReview> getAllBookreviews(Long isbnBook);

	boolean addBookReview(BookReview bookReview);

	BookReview checkIfUserHasDoneReview(Long isbnBook, Long iduser);

	Double calculateBookRating(Long isbnBook);

}
