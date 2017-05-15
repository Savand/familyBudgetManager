package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.Meansflow;
import org.andsav.family_budget_manager.repository.MeansflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMeansflowRepositoryImpl implements MeansflowRepository {

    @Autowired
    CrudMeansflowRepository crudRepository;

    @Override
    public Meansflow save(Meansflow meansFlow) {
        return crudRepository.save(meansFlow);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Meansflow get(int meansFlowId) {
        return crudRepository.findOne(meansFlowId);
    }

    @Override
    public List<Meansflow> getByBudgetId(Integer budgetId) {
        return crudRepository.getByBudgetId(budgetId);
    }

    @Override
    public List<Meansflow> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate) {
        return crudRepository.getByBudgetIdAndOperationDateTimeBetween(budgetId, startDate, endDate);
    }


}
