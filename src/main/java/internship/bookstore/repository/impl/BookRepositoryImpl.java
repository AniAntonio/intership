package internship.bookstore.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import internship.bookstore.entities.Book;
import internship.bookstore.repository.BookRepository;

@Repository
public class BookRepositoryImpl implements BookRepository {
	@PersistenceContext
	EntityManager entityManager;

	public List<Book> getAllBooksByIdUser(Long iduser) {
		List<Book> books = new ArrayList<Book>();
		try {

			TypedQuery<Book> booksQuery = entityManager.createQuery(
					"Select book from Book book where book.user.id=:iduser and book.valid=:valid", Book.class);
			booksQuery.setParameter("iduser", iduser);
			booksQuery.setParameter("valid", Boolean.TRUE);
			books = booksQuery.getResultList();
			return books;
		} catch (Exception e) {
			return books;
		}
	}

	public Book getBookByTitle(String title, Long iduser) {
		Book book = new Book();
		try {
			TypedQuery<Book> bookQuery = entityManager.createQuery(
					"Select book from Book book where book.title=:title and book.user.id=:iduser and book.valid=:valid",
					Book.class);
			bookQuery.setParameter("title", title);
			bookQuery.setParameter("iduser", iduser);
			bookQuery.setParameter("valid", Boolean.TRUE);
			book = bookQuery.getSingleResult();
			return book;
		} catch (Exception e) {
			return book;
		}
	}

	public boolean addBook(Book book) {
		try {
			book.setValid(Boolean.TRUE);
			entityManager.persist(book);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean editBook(Book book) {
		try {
			entityManager.merge(book);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteBook(Book book) {
		try {
			book.setValid(Boolean.FALSE);
			entityManager.merge(book);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
