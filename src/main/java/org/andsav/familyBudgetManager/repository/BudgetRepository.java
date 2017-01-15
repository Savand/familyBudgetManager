package org.andsav.familyBudgetManager.repository;

import java.util.List;

import org.andsav.familyBudgetManager.model.Budget;

public interface BudgetRepository {
	
	public Budget save(Budget budget);
	
	public boolean delete(Integer budgetId);
	
	public Budget get (Integer budgetId);
	
	public List<Budget> getBudgetsByUser(Integer userId);
	
}
