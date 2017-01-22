package org.andsav.familyBudgetManager.repository;

import java.util.List;

import org.andsav.familyBudgetManager.model.Budget;

public interface BudgetRepository {
	
	Budget save(Budget budget);
	
	boolean delete(Integer budgetId);
	
	Budget get (Integer budgetId);
	
	List<Budget> getBudgetsByUser(Integer userId);
	
}
