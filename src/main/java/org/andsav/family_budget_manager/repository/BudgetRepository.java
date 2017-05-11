package org.andsav.family_budget_manager.repository;

import org.andsav.family_budget_manager.model.Budget;

import java.util.List;

public interface BudgetRepository {

    Budget save(Budget budget);

    boolean delete(int id);

    List<Budget> getAll();

    Budget get(int id);

}
