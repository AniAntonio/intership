package internship.bookstore.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import internship.bookstore.entities.Author;
import internship.bookstore.repository.AuthorRepository;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Author> getAllAuthors() {
		List<Author> authors = new ArrayList<Author>();
		try {
			TypedQuery<Author> authorsQuery = entityManager
					.createQuery("Select author FROM Author author where author.deleted is false", Author.class);
			authors = authorsQuery.getResultList();
			return authors;
		} catch (Exception e) {
			return authors;
		}
	}

	public Author getAuthorByFirstNameAndLastName(String firstname, String lastname) {
		Author author = new Author();
		try {
			TypedQuery<Author> authorQuery = entityManager.createQuery(
					"Select author FROM Author author where author.firstname=:firstname and author.lastname=:lastname and author.deleted is false",
					Author.class);
			authorQuery.setParameter("firstname", firstname);
			authorQuery.setParameter("lastname", lastname);
			author = authorQuery.getSingleResult();
			return author;
		} catch (Exception e) {
			return author;
		}
	}

	@Override
	public Author getAuthorById(Long id) {
		Author author = new Author();
		try {
			TypedQuery<Author> authorQuery = entityManager.createQuery(
					"Select author FROM Author author where author.id=:id and author.deleted is false", Author.class);
			authorQuery.setParameter("id", id);
			author = authorQuery.getSingleResult();
			return author;
		} catch (Exception e) {
			return author;
		}

	}

	@Override
	public boolean addAuthor(Author author) {
		try {
			author.setDeleted(Boolean.FALSE);
			entityManager.persist(author);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean editAuthor(Author author) {
		try {
			author.setDeleted(Boolean.FALSE);
			entityManager.merge(author);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteAuthor(Author author) {
		try {
			author.setDeleted(Boolean.TRUE);
			entityManager.merge(author);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Author> getAuthorsByIdList(List<Long> idList) {
		TypedQuery<Author> authorsQuery = entityManager.createQuery(
				"Select author FROM Author author where author.deleted is false and author.id IN :idList",
				Author.class);
		authorsQuery.setParameter("idList", idList);
		return authorsQuery.getResultList();
	}

}
