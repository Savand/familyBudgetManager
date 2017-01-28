package org.andsav.familyBudgetManager.repository;

import java.util.List;

import org.andsav.familyBudgetManager.model.Budget;

public interface BudgetRepository {
	
  Budget save(Budget user);
  
  boolean delete(int id);
  
  List<Integer> getIdsByUserId(Integer userId);

  List<Budget> getAll();

  Budget get(int id);
	
}
