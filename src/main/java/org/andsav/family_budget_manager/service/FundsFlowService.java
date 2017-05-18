package org.andsav.family_budget_manager.service;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface FundsFlowService {

    FundsFlow save(FundsFlow meansFlow);

    void delete(int id) throws NotFoundException;

    void update(FundsFlow meansFlow) throws NotFoundException;

    List<FundsFlow> getbyBudgetId(Integer budgetId);

    List<FundsFlow> getBetweenDateByBudgetId(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate);

    FundsFlow get(int i) throws NotFoundException;

}
