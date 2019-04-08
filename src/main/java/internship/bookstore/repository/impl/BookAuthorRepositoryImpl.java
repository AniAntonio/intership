package internship.bookstore.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import internship.bookstore.entities.BookAuthor;
import internship.bookstore.repository.BookAuthorRepository;

@Repository
public class BookAuthorRepositoryImpl implements BookAuthorRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public boolean addBookAuthor(BookAuthor bookAuthor) {
		try {
			entityManager.persist(bookAuthor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteBookAuthors(BookAuthor bookAuthor) {
		try {
			entityManager.remove(bookAuthor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean checkIfAuthorHasBooks(Long idauthor) {
		List<BookAuthor> bookAuthors = new ArrayList<BookAuthor>();
		try {
			TypedQuery<BookAuthor> bookAuthorQuery = entityManager.createQuery(
					"Select bookAuthor FROM BookAuthor bookAuthor where bookAuthor.author.id=:idauthor",
					BookAuthor.class);
			bookAuthorQuery.setParameter("idauthor", idauthor);
			bookAuthors = bookAuthorQuery.getResultList();
			return bookAuthors.isEmpty();
		} catch (Exception e) {
			return false;
		}

	}

}
