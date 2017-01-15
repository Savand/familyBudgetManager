package org.andsav.familyBudgetManager.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.andsav.familyBudgetManager.model.Outcome;

public interface OutcomeRepository {

  public Outcome save(Outcome outcome);
  
  public boolean delete(Integer outcomeId);
  
  public Outcome get(Integer outcomeId);
  
  public List<Outcome> getByBudgetId(Integer budgetId);

  public List<Outcome> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate, LocalDateTime endDate);
  
}
