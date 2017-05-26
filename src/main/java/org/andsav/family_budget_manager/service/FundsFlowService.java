package org.andsav.family_budget_manager.service;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface FundsFlowService {

    FundsFlow save(FundsFlow meansFlow, int budgetId);

    void delete(int id, int budgetId) throws NotFoundException;

    void update(FundsFlow meansFlow, int budgetId) throws NotFoundException;

    List<FundsFlow> getAll(int budgetId);

    List<FundsFlow> getBetweenDateByBudgetId(int budgetId, LocalDateTime startDate,
            LocalDateTime endDate);

    FundsFlow get(int i, int budgetId) throws NotFoundException;

}
