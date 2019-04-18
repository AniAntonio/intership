package internship.bookstore.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import internship.bookstore.entities.Role;
import internship.bookstore.entities.User;
import internship.bookstore.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	EntityManager entityManager;

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			TypedQuery<User> usersQuery = entityManager
					.createQuery("Select user from User user where user.deleted is false", User.class);
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
					"Select user from User user where user.username=:username and user.password=:password and user.deleted is false",User.class);
			userQuery.setParameter("username", username);
			userQuery.setParameter("password", password);
			user = userQuery.getSingleResult();
			return user;
		} catch (Exception e) {
			return user;
		}
	}

	public User getUserByUsername(String username) {
		User user = new User();
		try {
			TypedQuery<User> userQuery = entityManager.createQuery(
					"Select user from User user where user.username=:username and user.deleted is false",
					User.class);
			userQuery.setParameter("username", username);
			user = userQuery.getSingleResult();
			return user;
		} catch (Exception e) {
			return user;
		}
	}

	public boolean addUser(User user) {
		try {
			Role role = new Role();
			role.setId(2L);
			user.setRole(role);
			user.setDeleted(Boolean.FALSE);
			entityManager.persist(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteUser(User user) {
		try {
			user.setDeleted(Boolean.TRUE);
			entityManager.merge(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean editUser(User user) {
		try {
			user.setDeleted(Boolean.FALSE);
			entityManager.merge(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
