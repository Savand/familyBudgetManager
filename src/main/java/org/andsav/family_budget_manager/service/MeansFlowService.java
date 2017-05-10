package org.andsav.family_budget_manager.service;

import org.andsav.family_budget_manager.model.Meansflow;
import org.andsav.family_budget_manager.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface MeansFlowService {

    Meansflow save(Meansflow meansFlow);

    void delete(int id) throws NotFoundException;

    void update(Meansflow meansFlow) throws NotFoundException;

    List<Meansflow> getbyBudgetId(Integer budgetId);

    List<Meansflow> getBetweenDateByBudgetId(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate);

    Meansflow get(int i) throws NotFoundException;

}
