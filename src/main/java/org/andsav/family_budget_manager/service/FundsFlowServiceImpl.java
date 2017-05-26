package org.andsav.family_budget_manager.service;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.repository.FundsFlowRepository;
import org.andsav.family_budget_manager.util.exception.ExceptionUtil;
import org.andsav.family_budget_manager.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FundsFlowServiceImpl implements FundsFlowService {

    @Autowired
    private FundsFlowRepository repository;


    @Override
    public List<FundsFlow> getAll(int budgetId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.getAll(budgetId), budgetId);
    }

    @Override
    public List<FundsFlow> getBetweenDateByBudgetId(int budgetId, LocalDateTime startDate,
            LocalDateTime endDate) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(
                repository.getAllBetweenDates(budgetId, startDate, endDate), budgetId);
    }

    @Override
    public FundsFlow save(FundsFlow fundsFlow, int budgetId) {
        Assert.notNull(fundsFlow, "meansFlow must not be null");
        return repository.save(fundsFlow, budgetId);
    }

    @Override
    public void delete(int id, int budgetId) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, budgetId), id);
    }

    @Override
    public void update(FundsFlow fundsFlow, int budgetId) throws NotFoundException {
        Assert.notNull(fundsFlow, "meansFlow must not be null");
        repository.save(fundsFlow, budgetId);
    }

    @Override
    public FundsFlow get(int id, int budgetId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, budgetId), id);
    }

}
