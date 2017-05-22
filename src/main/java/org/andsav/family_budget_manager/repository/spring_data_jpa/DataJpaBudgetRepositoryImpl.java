package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaBudgetRepositoryImpl implements BudgetRepository {

    @Autowired
    CrudBudgetRepository crudRepository;

    @Override
    public Budget save(Budget budget) {
        return crudRepository.save(budget);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public List<Budget> getAll() {
        return crudRepository.findAll(new Sort("id"));
    }

    @Override
    public Budget get(int id) {
        return crudRepository.findOne(id);
    }

}
