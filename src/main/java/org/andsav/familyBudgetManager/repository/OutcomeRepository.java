package org.andsav.familyBudgetManager.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.andsav.familyBudgetManager.model.Outcome;

public interface OutcomeRepository {

  Outcome save(Outcome outcome);
  
  boolean delete(Integer outcomeId);
  
  Outcome get(Integer outcomeId);
  
  List<Outcome> getByBudgetId(Integer budgetId);

  List<Outcome> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate, LocalDateTime endDate);
  
}
