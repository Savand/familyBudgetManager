package org.andsav.familyBudgetManager.repository;

import java.sql.SQLException;

import org.andsav.familyBudgetManager.model.Budget;

public interface BudgetRepository {
	
	public Budget save(Budget budget) throws SQLException;
	
	public boolean delete(Integer budgetId) throws SQLException;
	
	public Budget get (Integer budgetId)  throws SQLException;
	
	
	
}
