package org.andsav.familyBudgetManager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.andsav.familyBudgetManager.model.MeansFlow;
import org.andsav.familyBudgetManager.util.exception.NotFoundException;

public interface MeansFlowService {
  
  MeansFlow save(MeansFlow meansFlow);
  
  void delete(int id) throws NotFoundException;
  
  void update(MeansFlow meansFlow) throws NotFoundException;

  List<MeansFlow> getbyBudgetId(Integer budgetId);
  
  List<MeansFlow> getBetweenDateByBudgetId(Integer budgetId, LocalDateTime startDate, LocalDateTime endDate);

  MeansFlow get(int i) throws NotFoundException;
  
}
