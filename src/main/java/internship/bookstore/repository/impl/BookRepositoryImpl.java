package internship.bookstore.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import internship.bookstore.entities.Author;
import internship.bookstore.entities.Book;
import internship.bookstore.repository.BookRepository;

@Repository
public class BookRepositoryImpl implements BookRepository {
	@PersistenceContext
	EntityManager entityManager;

	public List<Book> getAllBooks(String searchedTitle, Author author, int pageNumber) {
		List<Book> books = new ArrayList<>();
		try {
			StringBuilder booksQuery = new StringBuilder(
					"Select book from Book book join fetch book.authors authors where book.deleted is false");
			if (searchedTitle != null) {
				booksQuery.append("  AND (LOWER(book.title) LIKE LOWER(CONCAT('%',:searchedTitle, '%')))");
			}
			if (author.getId() != null) {
				booksQuery.append(" AND (:author IN authors)");
			}
			booksQuery.append(" order by rating desc");
			TypedQuery<Book> booksTypedQuery = entityManager.createQuery(booksQuery.toString(), Book.class);
			if (searchedTitle != null) {
				booksTypedQuery.setParameter("searchedTitle", searchedTitle);
			}
			if (author.getId() != null) {
				booksTypedQuery.setParameter("author", author);
			}
			if (pageNumber == 0) {
				booksTypedQuery.setFirstResult(0);

			} else {
				booksTypedQuery.setFirstResult((pageNumber - 1) * 5);
			}
			booksTypedQuery.setMaxResults(5);
			return booksTypedQuery.getResultList();
		} catch (Exception e) {
			return books;
		}
	}

	public int countBooks(String searchedTitle, Author author) {
		try {
			StringBuilder booksQuery = new StringBuilder(
					"Select DISTINCT book from Book book join fetch book.authors authors where book.deleted is false");
			if (searchedTitle != null) {
				booksQuery.append("  AND (LOWER(book.title) LIKE LOWER(CONCAT('%',:searchedTitle, '%')))");
			}
			if (author != null) {
				booksQuery.append(" AND (:author IN authors)");
			}

			TypedQuery<Book> booksTypedQuery = entityManager.createQuery(booksQuery.toString(), Book.class);
			if (searchedTitle != null) {
				booksTypedQuery.setParameter("searchedTitle", searchedTitle);
			}
			if (author != null) {
				booksTypedQuery.setParameter("author", author);
			}
			return booksTypedQuery.getResultList().size();
		} catch (Exception e) {
			return 0;
		}

	}

	public Book getBookByTitle(String title) {
		try {
			TypedQuery<Book> bookQuery = entityManager.createQuery(
					"Select book from Book book where book.title=:title and book.deleted is false", Book.class);
			bookQuery.setParameter("title", title);
			return bookQuery.getSingleResult();
		} catch (Exception e) {
			return new Book();
		}
	}

	public Book getBookByIsbn(Long isbn) {
		try {
			TypedQuery<Book> bookQuery = entityManager.createQuery(
					"Select book from Book book where book.isbn=:isbn and  book.deleted is false", Book.class);
			bookQuery.setParameter("isbn", isbn);
			return bookQuery.getSingleResult();
		} catch (Exception e) {
			return new Book();
		}
	}

	public boolean saveBook(Book book) {
		try {
			book.setDeleted(Boolean.FALSE);
			entityManager.merge(book);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteBook(Book book) {
		try {
			book.setDeleted(Boolean.TRUE);
			entityManager.merge(book);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
