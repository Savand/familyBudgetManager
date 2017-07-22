package org.andsav.family_budget_manager.repository.spring_data_jpa;

import java.util.List;

import org.andsav.family_budget_manager.model.User;
import org.andsav.family_budget_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

  private static final String SORT_BY_NAME_EMAIL[] = { "name", "email" };

  @Autowired
  CrudUserRepository crudRepository;

  @Override
  public User save(User user) {
    return crudRepository.save(user);
  }

  @Override
  public boolean delete(int id) {
    return crudRepository.delete(id) != 0;
  }

  @Override
  public User get(int id) {
    return crudRepository.findOne(id);
  }

  @Override
  public User getByEmail(String email) {
    return crudRepository.getByEmail(email);
  }

  @Override
  public List<User> getAll() {
    return crudRepository.findAll(new Sort(SORT_BY_NAME_EMAIL));
  }

}
