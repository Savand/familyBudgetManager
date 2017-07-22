package org.andsav.family_budget_manager.service;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.util.exception.NotFoundException;

import java.util.Set;

/**
 * Service to manage the budgets. In most cases should be provided creator Id as
 * parameter to provide budget manipulating only by budget creator, not
 * contributors
 * 
 * 
 * @author Andrii_Savka
 *
 */

public interface BudgetService {

  /**
   * Create new budget and add creator as contributor
   * 
   * @param budget
   * @param creatorId
   * @return
   */
  Budget create(Budget budget, int creatorId);

  /**
   * Check if the user with specified id is a contributor
   * 
   * @param budget
   * @param creatorId
   * @return
   */
  boolean isContributor(int id, int creatorId);

  /**
   * delete budget by budget id and creator id
   * 
   * @param id
   * @param creatorId
   * @throws NotFoundException
   */
  void delete(int id, int creatorId) throws NotFoundException;

  /**
   * update budget by budget id and creator id
   * 
   * @param budget
   * @param creatorId
   * @throws NotFoundException
   */
  void update(Budget budget, int creatorId) throws NotFoundException;

  /**
   * 
   * 
   * @param budget
   * @param userId
   * @param creatorId
   * @throws NotFoundException
   */
  void addContributorToBudget(Budget budget, int userId, int creatorId) throws NotFoundException;

  /**
   * get budgets that are allowed for user to contribute
   * 
   * @param contributorId
   * @return
   */
  Set<Budget> getAll(int contributorId);

  /**
   * get budget by it's id
   * 
   * @param id
   * @return
   * @throws NotFoundException
   */
  Budget get(int id) throws NotFoundException;

  Budget getBudgetWithContributors(int id) throws NotFoundException;
  // Set<Budget> getByUserEmail(String email) throws NotFoundException;

}
