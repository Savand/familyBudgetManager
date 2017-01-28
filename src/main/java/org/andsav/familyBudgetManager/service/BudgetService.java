package org.andsav.familyBudgetManager.service;

import java.util.List;

import org.andsav.familyBudgetManager.model.Budget;
import org.andsav.familyBudgetManager.util.exception.NotFoundException;

public interface BudgetService {
  
  Budget save(Budget budget);
  
  void delete(int id) throws NotFoundException;
  
  void update(Budget budget) throws NotFoundException;

  List<Budget> getbyUserId(Integer userId);

  List<Budget> getAll();

  Budget get(int i);
  
}
