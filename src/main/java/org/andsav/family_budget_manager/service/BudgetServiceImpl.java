package org.andsav.family_budget_manager.service;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.model.User;
import org.andsav.family_budget_manager.repository.BudgetRepository;
import org.andsav.family_budget_manager.util.exception.ExceptionUtil;
import org.andsav.family_budget_manager.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;

@Service
public class BudgetServiceImpl implements BudgetService {

  @Autowired
  private BudgetRepository repository;

  @Autowired
  private UserService userService;

  @Override
  public Budget create(Budget budget, int userId) {
    Assert.notNull(budget, "budget must not be null");
    User budgetCreator = userService.get(userId);
    if (budget.getBudgetCreator() == null) {
      budget.setBudgetCreator(budgetCreator);
    }
    budget.addContributor(budgetCreator);
    return repository.save(budget, userId);
  }

  @Override
  public void delete(int id, int userId) throws NotFoundException {
    ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId), id);
  }

  @Override
  public void update(Budget budget, int userId) throws NotFoundException {
    Assert.notNull(budget, "budget must not be null");
    ExceptionUtil.checkNotFoundWithId(repository.save(budget, userId), budget.getId());
  }

  @Override
  public Set<Budget> getAll(int userId) {
    return repository.getAll(userId);
  }

  @Override
  public Budget get(int id) throws NotFoundException {
    return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
  }

  @Override
  public void addContributorToBudget(Budget budget, int userId, int creatorId) throws NotFoundException {
    Assert.notNull(budget, "budget must not be null");

    budget.addContributor(userService.get(userId));
    update(budget, creatorId);
  }

  @Override
  public boolean isContributor(int id, int userId) {
    Set<User> contributors = getContributors(id);
    User user = userService.get(userId);
    return contributors.contains(user);
  }

  @Override
  public Budget getBudgetWithContributors(int id) throws NotFoundException {
    return repository.getBudgetWithContributors(id);
  }

  private Set<User> getContributors(int id) {
    return getBudgetWithContributors(id).getBudgetContributors();
  }

}
