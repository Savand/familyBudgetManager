package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CrudBudgetRepository extends JpaRepository<Budget, Integer>{

    @Modifying
    @Query(name = Budget.DELETE)
    int delete(@Param("id") int id);
}
