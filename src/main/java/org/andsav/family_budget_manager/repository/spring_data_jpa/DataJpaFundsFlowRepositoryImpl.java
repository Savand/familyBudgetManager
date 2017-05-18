package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.repository.FundsflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaFundsFlowRepositoryImpl implements FundsflowRepository {

    @Autowired
    CrudFundsFlowRepository crudRepository;

    @Override
    public FundsFlow save(FundsFlow meansFlow) {
        return crudRepository.save(meansFlow);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public FundsFlow get(int meansFlowId) {
        return crudRepository.findOne(meansFlowId);
    }

    @Override
    public List<FundsFlow> getByBudgetId(Integer budgetId) {
        return crudRepository.getByBudgetId(budgetId);
    }

    @Override
    public List<FundsFlow> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate) {
        return crudRepository.getByBudgetIdAndOperationDateTimeBetween(budgetId, startDate, endDate);
    }


}
