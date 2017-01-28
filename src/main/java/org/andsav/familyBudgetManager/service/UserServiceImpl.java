package org.andsav.familyBudgetManager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.andsav.familyBudgetManager.model.User;
import org.andsav.familyBudgetManager.repository.UserRepository;
import org.andsav.familyBudgetManager.util.exception.ExceptionUtil;
import org.andsav.familyBudgetManager.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  private UserRepository repository;
  
  @Override
  public User save(User user) {
    Assert.notNull(user, "user must not be null");
    return repository.save(user);
  }

  @Override
  public void delete(int id) throws NotFoundException {
    ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
  }

  @Override
  public User get(int id) throws NotFoundException {
    return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
  }

  @Override
  public User getByEmail(String email) throws NotFoundException {
    Assert.notNull(email, "email must not be null");
    return ExceptionUtil.checkNotFound(repository.getByEmail(email.toLowerCase()), "email=" + email);
  }

  @Override
  public List<User> getAll() {
    return repository.getAll();
  }

  @Override
  public List<User> getbyBudgetId(Integer budgetId) {
    List<Integer> ids = repository.getIdsByBudgetId(budgetId);
    List<User> all = getAll();
    
    List<User> userResult = all.stream()
    .filter(u -> ids.contains(u.getId()))
    .collect(Collectors.toList());
    
    return userResult;
  }

  @Override
  public void update(User user) throws NotFoundException {
    Assert.notNull(user, "user must not be null"); 
    repository.save(user);
  }


}
