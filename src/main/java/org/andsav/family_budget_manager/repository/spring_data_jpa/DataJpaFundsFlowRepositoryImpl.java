package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.repository.FundsFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaFundsFlowRepositoryImpl implements FundsFlowRepository {

    @Autowired
    CrudFundsFlowRepository crudRepository;
    @Autowired
    CrudBudgetRepository crudBudgetRepository;

    @Override
    public FundsFlow save(FundsFlow fundsFlow, int budgetId) {
        if (!fundsFlow.isNew() && get(fundsFlow.getId(), budgetId) == null) {
            return null;
        }
        
        fundsFlow.setBudget(crudBudgetRepository.getOne(budgetId));
        return crudRepository.save(fundsFlow);
    }

    @Override
    public boolean delete(int id, int budgetId) {
        return crudRepository.delete(id, budgetId) != 0;
    }

    @Override
    public FundsFlow get(int id, int budgetId) {
        return crudRepository.getByIdAndBudgetId(id, budgetId);
    }

    @Override
    public List<FundsFlow> getAll(int budgetId) {
        return crudRepository.getByBudgetId(budgetId);
    }

    @Override
    public List<FundsFlow> getAllBetweenDates(int budgetId, LocalDateTime startDate,
            LocalDateTime endDate) {
        return crudRepository.getByBudgetIdAndOperationDateTimeBetween(budgetId, startDate, endDate);
    }


}
