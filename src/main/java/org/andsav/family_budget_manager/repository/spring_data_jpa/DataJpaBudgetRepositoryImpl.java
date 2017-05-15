package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaBudgetRepositoryImpl implements BudgetRepository {

    @Autowired
    CrudBudgetRepository crudRepository;

    @Override
    public Budget save(Budget budget) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Budget> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Budget get(int id) {
        // TODO Auto-generated method stub
        return null;
    }

}
