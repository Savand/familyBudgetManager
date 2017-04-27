package org.andsav.family_budget_manager.repository;

import org.andsav.family_budget_manager.model.MeansFlow;

import java.time.LocalDateTime;
import java.util.List;

public interface MeansFlowRepository {

    MeansFlow save(MeansFlow meansFlow);

    boolean delete(Integer meansFlowId);

    MeansFlow get(Integer meansFlowId);

    List<MeansFlow> getByBudgetId(Integer budgetId);

    List<MeansFlow> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate);

}
