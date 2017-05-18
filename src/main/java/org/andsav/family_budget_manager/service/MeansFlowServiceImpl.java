package org.andsav.family_budget_manager.service;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.repository.FundsflowRepository;
import org.andsav.family_budget_manager.util.exception.ExceptionUtil;
import org.andsav.family_budget_manager.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeansFlowServiceImpl implements FundsFlowService {

    @Autowired
    private FundsflowRepository repository;

    @Override
    public FundsFlow save(FundsFlow meansFlow) {
        Assert.notNull(meansFlow, "meansFlow must not be null");
        return repository.save(meansFlow);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public void update(FundsFlow meansFlow) {
        Assert.notNull(meansFlow, "meansFlow must not be null");
        repository.save(meansFlow);
    }

    @Override
    public List<FundsFlow> getbyBudgetId(Integer budgetId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.getByBudgetId(budgetId), budgetId);
    }

    @Override
    public List<FundsFlow> getBetweenDateByBudgetId(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(
                repository.getByBudgetIdBetweenDates(budgetId, startDate, endDate), budgetId);
    }

    @Override
    public FundsFlow get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

}
