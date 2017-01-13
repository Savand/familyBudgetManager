package org.andsav.familyBudgetManager.repository;

import java.sql.SQLException;
import java.util.List;

import org.andsav.familyBudgetManager.model.User;

public interface UserRepository {
  User save(User user) throws SQLException;
  
  boolean delete(int id) throws SQLException;
  
  User get(int id) throws SQLException;
  
  User getByEmail(String email) throws SQLException;
  
  List<User> getAll() throws SQLException;
}
