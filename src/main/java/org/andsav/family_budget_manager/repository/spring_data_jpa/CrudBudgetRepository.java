package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudBudgetRepository extends JpaRepository<Budget, Integer>{

}
