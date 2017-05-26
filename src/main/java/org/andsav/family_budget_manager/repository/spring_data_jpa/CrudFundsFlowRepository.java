package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudFundsFlowRepository extends JpaRepository<FundsFlow, Integer>{
    
    @Modifying
    @Query(name = FundsFlow.DELETE)
    @Transactional
    int delete(@Param("id") int id, @Param("budgetId") int budgetId);
    
    List<FundsFlow> getByBudgetId(int budgetId);

    List<FundsFlow> getByBudgetIdAndOperationDateTimeBetween(int budgetId, LocalDateTime startDate,
            LocalDateTime endDate);
    
    FundsFlow getByIdAndBudgetId(int id, int budgetId);
}
