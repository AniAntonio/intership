package internship.bookstore.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import internship.bookstore.entities.BookReview;
import internship.bookstore.repository.BookReviewRepository;

@Repository
public class BookReviewRepositoryImpl implements BookReviewRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<BookReview> getAllBookreviews(Long isbnBook) {
		TypedQuery<BookReview> reviewsQuery = entityManager.createQuery(
				"Select review from BookReview review where review.book.isbn=:isbnbook order by (review.thumbup*review.vote)",
				BookReview.class);
		reviewsQuery.setParameter("isbnbook", isbnBook);
		return reviewsQuery.getResultList();
	}

	@Override
	public boolean addBookReview(BookReview bookReview) {
		try {
			entityManager.persist(bookReview);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public BookReview checkIfUserHasDoneReview(Long isbnBook, Long iduser) {
		BookReview bookReview = new BookReview();
		try {
			TypedQuery<BookReview> reviewsQuery = entityManager.createQuery(
					"Select review from BookReview review where review.book.isbn=:isbnbook and review.user.id=:iduser",
					BookReview.class);
			reviewsQuery.setParameter("isbnbook", isbnBook);
			reviewsQuery.setParameter("iduser", iduser);
			return reviewsQuery.getSingleResult();
		} catch (Exception e) {
			return bookReview;
		}
	}

	@Override
	public Double calculateBookRating(Long isbnBook) {
		TypedQuery reviewsQuery = (TypedQuery) entityManager.createQuery(
				"Select AVG(vote)/10+AVG(thumbup)  FROM  BookReview review where review.book.isbn=:isbnbook");
		reviewsQuery.setParameter("isbnbook", isbnBook);

		return (Double) reviewsQuery.getSingleResult();
	}

}
