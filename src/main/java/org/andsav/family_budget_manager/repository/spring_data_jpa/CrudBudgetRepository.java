package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.Budget;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface CrudBudgetRepository extends JpaRepository<Budget, Integer> {

  @Modifying
  @Query(name = Budget.DELETE)
  @Transactional
  int delete(@Param("id") int id, @Param("userId") int userId);

  Set<Budget> getByBudgetContributorsId(int userId);

  /**
   * load budget with not null contributors
   * 
   * @param id
   * @return
   */
  @EntityGraph(attributePaths = { "budgetContributors" })
  Budget getById(int id);

}
