package org.andsav.family_budget_manager.service;

import org.andsav.family_budget_manager.model.MeansFlow;
import org.andsav.family_budget_manager.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface MeansFlowService {

    MeansFlow save(MeansFlow meansFlow);

    void delete(int id) throws NotFoundException;

    void update(MeansFlow meansFlow) throws NotFoundException;

    List<MeansFlow> getbyBudgetId(Integer budgetId);

    List<MeansFlow> getBetweenDateByBudgetId(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate);

    MeansFlow get(int i) throws NotFoundException;

}
