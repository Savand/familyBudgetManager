package org.andsav.familyBudgetManager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.andsav.familyBudgetManager.model.Budget;
import org.andsav.familyBudgetManager.repository.BudgetRepository;
import org.andsav.familyBudgetManager.util.exception.ExceptionUtil;
import org.andsav.familyBudgetManager.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class BudgetServiceImpl implements BudgetService {
  
  @Autowired
  private BudgetRepository repository;
  
  @Override
  public Budget save(Budget budget) {
    Assert.notNull(budget, "budget must not be null");
    return repository.save(budget);
  }

  @Override
  public void delete(int id) throws NotFoundException {
    ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
  }

  @Override
  public void update(Budget budget) throws NotFoundException {
    Assert.notNull(budget, "budget must not be null"); 
    repository.save(budget);
  }


  @Override
  public List<Budget> getbyUserId(Integer userId) {
    List<Integer> ids = repository.getIdsByUserId(userId);
    List<Budget> all = getAll();
    
    List<Budget> budgetsResult = all.stream()
    .filter(b -> ids.contains(b.getId()))
    .collect(Collectors.toList());
    
    return budgetsResult;
  }

  @Override
  public List<Budget> getAll() {
    return repository.getAll();
  }

  @Override
  public Budget get(int id) {
    return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
  }

}
