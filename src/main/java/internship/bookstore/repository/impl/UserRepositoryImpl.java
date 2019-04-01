package internship.bookstore.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> query = builder.createQuery(User.class);
    Root<User> root = query.from(User.class);

    query.where(new Predicate[] {});
    query.select(root).distinct(true);
    
    List<User> users = entityManager.createQuery(query).getResultList();
    return users;
  }
  
}
