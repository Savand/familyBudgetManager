package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class DataJpaBudgetRepositoryImpl implements BudgetRepository {

    @Autowired
    CrudBudgetRepository crudRepository;

    @Override
    public Budget save(Budget budget, int userId) {
        crudRepository.save(budget);
        return budget;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Budget get(int id) {
        return crudRepository.findOne(id);
    }

    @Override
    public Set<Budget> getAll(int userId) {
        return crudRepository.getByBudgetContributorsId(userId);
    }

    @Override
    public Budget getBudgetWithContributors(int id) {
        return crudRepository.getById(id);
    }

}
