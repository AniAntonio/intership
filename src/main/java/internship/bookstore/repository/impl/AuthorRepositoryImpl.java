package internship.bookstore.repository.impl;

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

		TypedQuery<Author> authorsQuery = entityManager
				.createQuery("Select author FROM Author author where author.deleted is false", Author.class);
		return authorsQuery.getResultList();

	}

	public Author getAuthorByFirstNameAndLastName(String firstname, String lastname) {
		try {
			TypedQuery<Author> authorQuery = entityManager.createQuery(
					"Select author FROM Author author where author.firstname=:firstname and author.lastname=:lastname and author.deleted is false",
					Author.class);
			authorQuery.setParameter("firstname", firstname);
			authorQuery.setParameter("lastname", lastname);
			return authorQuery.getSingleResult();
		} catch (Exception e) {
			return new Author();
		}
	}

	@Override
	public Author getAuthorById(Long id) {
		try {
			TypedQuery<Author> authorQuery = entityManager.createQuery(
					"Select author FROM Author author where author.id=:id and author.deleted is false", Author.class);
			authorQuery.setParameter("id", id);
			return authorQuery.getSingleResult();

		} catch (Exception e) {
			return new Author();
		}

	}

	@Override
	public boolean saveAuthor(Author author) {
		author.setDeleted(Boolean.FALSE);
		entityManager.merge(author);
		return true;
	}

	@Override
	public boolean deleteAuthor(Author author) {
		author.setDeleted(Boolean.TRUE);
		entityManager.merge(author);
		return true;
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
