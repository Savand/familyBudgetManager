package org.andsav.familyBudgetManager.repository;

import java.util.List;

import org.andsav.familyBudgetManager.model.User;

public interface UserRepository {
  
  User save(User user);
  
  boolean delete(int id);
  
  User get(int id);
  
  User getByEmail(String email);
  
  List<User> getAll();
  
  List<Integer> getIdsByBudgetId(Integer budgetId);

}
