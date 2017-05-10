package org.andsav.family_budget_manager.service;

import org.andsav.family_budget_manager.model.Meansflow;
import org.andsav.family_budget_manager.repository.MeansflowRepository;
import org.andsav.family_budget_manager.util.exception.ExceptionUtil;
import org.andsav.family_budget_manager.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeansFlowServiceImpl implements MeansFlowService {

    @Autowired
    private MeansflowRepository repository;

    @Override
    public Meansflow save(Meansflow meansFlow) {
        Assert.notNull(meansFlow, "meansFlow must not be null");
        return repository.save(meansFlow);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public void update(Meansflow meansFlow) {
        Assert.notNull(meansFlow, "meansFlow must not be null");
        repository.save(meansFlow);
    }

    @Override
    public List<Meansflow> getbyBudgetId(Integer budgetId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.getByBudgetId(budgetId), budgetId);
    }

    @Override
    public List<Meansflow> getBetweenDateByBudgetId(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(
                repository.getByBudgetIdBetweenDates(budgetId, startDate, endDate), budgetId);
    }

    @Override
    public Meansflow get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

}
