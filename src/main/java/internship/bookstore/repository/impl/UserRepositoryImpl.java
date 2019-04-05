package internship.bookstore.repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import internship.bookstore.entities.User;
import internship.bookstore.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	EntityManager entityManager;

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			TypedQuery<User> usersQuery = entityManager
					.createQuery("Select user from User user where user.valid=:valid", User.class);
			usersQuery.setParameter("valid", Boolean.TRUE);
			users = usersQuery.getResultList();
			return users;
		} catch (Exception e) {
			return users;
		}
	}

	public User getUserByUsernameAndPassword(String username, String password) {
		User user = new User();
		try {
			TypedQuery<User> userQuery = entityManager.createQuery(
					"Select user from User user where user.username=:username and user.password=:password and user.valid=:valid",
					User.class);
			userQuery.setParameter("username", username);
			userQuery.setParameter("password", password);
			userQuery.setParameter("valid", Boolean.TRUE);
			user = userQuery.getSingleResult();
			return user;
		} catch (Exception e) {
			return user;
		}
	}

	public boolean addUser(User user) {
		try {
			entityManager.getTransaction().begin();
			user.setValid(Boolean.TRUE);
			Query query = entityManager
					.createNativeQuery("INSERT INTO user (username,password,firstname,lastname,email,valid,idrole) VALUES (?,?,?,1)");
			query.setParameter(1, user.getUsername());
			query.setParameter(2, user.getPassword());
			query.setParameter(3, user.getFirstname());
			query.setParameter(4, user.getLastname());
			query.setParameter(5, user.getEmail());
			query.setParameter(6, user.isValid());
			query.setParameter(7, 2);
			query.executeUpdate();
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteUser(User user) {
		try {
			user.setValid(Boolean.FALSE);
			entityManager.merge(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean editUser(User user) {
		try {
			entityManager.merge(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
