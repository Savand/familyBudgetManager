package org.andsav.familyBudgetManager.repository;

import java.util.List;

import org.andsav.familyBudgetManager.model.User;

public interface UserRepository {
  User save(User user);
  
  boolean delete(int id);
  
  User getUser(int id);
  
  User getByEmail(String email);
  
  List<User> getAllUsers();
}
