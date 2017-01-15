package org.andsav.familyBudgetManager.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.andsav.familyBudgetManager.model.Income;

public interface IncomeRepository {
  
  public Income save(Income income);
  
  public boolean delete(Integer incomeId);
  
  public Income get(Integer incomeId);
  
  public List<Income> getByBudgetId(Integer budgetId);

  public List<Income> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate, LocalDateTime endDate);
  
}
