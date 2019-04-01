package internship.bookstore.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import internship.bookstore.entities.User;
import internship.bookstore.repository.UserRepository;
import internship.bookstore.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
	  return userRepository.getAllUsers();
	}
}
