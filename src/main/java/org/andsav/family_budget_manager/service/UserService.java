package org.andsav.family_budget_manager.service;

import org.andsav.family_budget_manager.model.User;
import org.andsav.family_budget_manager.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

  User save(User user);

  User get(int id) throws NotFoundException;

  void delete(int id) throws NotFoundException;

  void update(User user) throws NotFoundException;

  User getByEmail(String email) throws NotFoundException;

  List<User> getAll();

  void evictCache();

}
