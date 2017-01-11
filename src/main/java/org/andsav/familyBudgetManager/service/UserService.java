package org.andsav.familyBudgetManager.service;

import java.util.List;

import org.andsav.familyBudgetManager.model.User;
import org.andsav.familyBudgetManager.util.exception.NotFoundException;

public interface UserService {
  
  public User save(User user);
  
  public void delete(int id) throws NotFoundException;
  
  public User get(int id) throws NotFoundException;
  
  public User getByEmail(String email) throws NotFoundException;
  
  public List<User> getAll();
  
  public void update(User user) throws NotFoundException;
}
