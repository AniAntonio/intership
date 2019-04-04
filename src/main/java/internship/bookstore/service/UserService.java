package internship.bookstore.service;

import java.util.List;
import internship.bookstore.entities.User;

public interface UserService {

	List<User> getAllUsers();

	User getUserByUsernameAndPassword(String username, String password);

	boolean addUser(User user);
	
	boolean editUser(User user);
	
	boolean deleteUser(User user);
	
}
