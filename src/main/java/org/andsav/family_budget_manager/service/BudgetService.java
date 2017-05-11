package org.andsav.family_budget_manager.service;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.util.exception.NotFoundException;

import java.util.List;

public interface BudgetService {

    Budget save(Budget budget);

    void delete(int id) throws NotFoundException;

    void update(Budget budget) throws NotFoundException;

    List<Budget> getAll();

    Budget get(int i);

}
