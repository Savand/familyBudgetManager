package org.andsav.family_budget_manager.repository;

import org.andsav.family_budget_manager.model.FundsFlow;

import java.time.LocalDateTime;
import java.util.List;

public interface FundsFlowRepository {

  FundsFlow save(FundsFlow fundsFlow, int budgetId);

  boolean delete(int id, int budgetId);

  FundsFlow get(int id, int budgetId);

  List<FundsFlow> getAll(int budgetId);

  List<FundsFlow> getAllBetweenDates(int budgetId, LocalDateTime startDate, LocalDateTime endDate);

}
