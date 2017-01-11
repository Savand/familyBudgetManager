package org.andsav.familyBudgetManager.service;

import java.util.List;

import org.andsav.familyBudgetManager.model.User;
import org.andsav.familyBudgetManager.repository.UserRepository;
import org.andsav.familyBudgetManager.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  private UserRepository userRepository;
  
  @Override
  public User save(User user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(int id) throws NotFoundException {
    // TODO Auto-generated method stub

  }

  @Override
  public User get(int id) throws NotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public User getByEmail(String email) throws NotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<User> getAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(User user) throws NotFoundException {
    // TODO Auto-generated method stub

  }

}
