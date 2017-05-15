package org.andsav.family_budget_manager.repository;

import org.andsav.family_budget_manager.model.Meansflow;

import java.time.LocalDateTime;
import java.util.List;

public interface MeansflowRepository {

    Meansflow save(Meansflow meansFlow);

    boolean delete(int id);

    Meansflow get(int id);

    List<Meansflow> getByBudgetId(Integer budgetId);

    List<Meansflow> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate);

}
