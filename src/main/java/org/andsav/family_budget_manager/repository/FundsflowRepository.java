package org.andsav.family_budget_manager.repository;

import org.andsav.family_budget_manager.model.FundsFlow;

import java.time.LocalDateTime;
import java.util.List;

public interface FundsflowRepository {

    FundsFlow save(FundsFlow meansFlow);

    boolean delete(int id);

    FundsFlow get(int id);

    List<FundsFlow> getByBudgetId(Integer budgetId);

    List<FundsFlow> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate);

}
