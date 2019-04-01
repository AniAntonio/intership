package internship.bookstore.repository;

import java.util.List;
import internship.bookstore.entities.User;

public interface UserRepository {
  List<User> getAllUsers();
}
