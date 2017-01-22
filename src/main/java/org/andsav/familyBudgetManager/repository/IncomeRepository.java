package org.andsav.familyBudgetManager.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.andsav.familyBudgetManager.model.Income;

public interface IncomeRepository {
  
  Income save(Income income);
  
  boolean delete(Integer incomeId);
  
  Income get(Integer incomeId);
  
  List<Income> getByBudgetId(Integer budgetId);

  List<Income> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate, LocalDateTime endDate);
  
}
