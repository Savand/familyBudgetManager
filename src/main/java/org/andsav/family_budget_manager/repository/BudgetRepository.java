package org.andsav.family_budget_manager.repository;

import org.andsav.family_budget_manager.model.Budget;

import java.util.Set;

public interface BudgetRepository {

    Budget save(Budget budget, int userId);

    boolean delete(int id, int userId);

    Budget get(int id);

    Set<Budget> getAll(int contributorId);

    default Budget getBudgetWithContributors(int id) {
        throw new UnsupportedOperationException();
    };

}
