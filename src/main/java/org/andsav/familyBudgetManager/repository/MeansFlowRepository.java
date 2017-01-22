package org.andsav.familyBudgetManager.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.andsav.familyBudgetManager.model.MeansFlow;

public interface MeansFlowRepository {

  MeansFlow save(MeansFlow meansFlow);
  
  boolean delete(Integer meansFlowId);
  
  MeansFlow get(Integer meansFlowId);
  
  List<MeansFlow> getByBudgetId(Integer budgetId);

  List<MeansFlow> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate, LocalDateTime endDate);
  
}
