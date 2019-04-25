package internship.bookstore.repository;

import java.util.List;
import internship.bookstore.entities.User;

public interface UserRepository {
	List<User> getAllUsers();

	User getUserByUsernameAndPassword(String username, String password);

	User getUserByUsername(String username);

	boolean addUser(User user);

	boolean deleteUser(User user);

	boolean editUser(User user);
}
