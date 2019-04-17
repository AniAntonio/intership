package internship.bookstore.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import internship.bookstore.entities.User;
import internship.bookstore.repository.UserRepository;
import internship.bookstore.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		return userRepository.getUserByUsernameAndPassword(username, password);
	}

	@Override
	public boolean addUser(User user) {
		if(userRepository.getUserByUsername(user.getUsername()).getId() == null) {
			return userRepository.addUser(user);
		} else {
			return false;
		}
	}

	@Override
	public boolean editUser(User user) {
		return userRepository.editUser(user);
	}

	@Override
	public boolean deleteUser(User user) {
		return userRepository.deleteUser(user);
	}

}
