package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.Meansflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMeansflowRepository extends JpaRepository<Meansflow, Integer>{
    
    @Modifying
    @Query(name = Meansflow.DELETE)
    int delete(@Param("id") int id);
    
    List<Meansflow> getByBudgetId(Integer budgetId);

    List<Meansflow> getByBudgetIdAndOperationDateTimeBetween(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate);
}
