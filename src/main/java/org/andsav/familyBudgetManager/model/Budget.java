package org.andsav.familyBudgetManager.model;

import org.andsav.familyBudgetManager.model.abstractentity.NamedEntity;

public class Budget extends NamedEntity {
  
  private int budgetPerDay;
  
  private final Integer budgetAmount;
  
  private final User budgetCreator;
  

  public Budget(Long id, String budgetName, int budgetPerDay, Integer budgetAmount, User budgetCreator) {
    super(id, budgetName);
    this.budgetPerDay = budgetPerDay;
    this.budgetAmount = budgetAmount;
    this.budgetCreator = budgetCreator;
  }
  
  public Budget(String budgetName, int budgetPerDay, Integer budgetAmount, User budgetCreator) {
    this(null, budgetName, budgetPerDay, budgetAmount, budgetCreator);
  }
  
  public int getBudgetPerDay() {
    return budgetPerDay;
  }

  public void setBudgetPerDay(int budgetPerDay) {
    this.budgetPerDay = budgetPerDay;
  }

  public Integer getBudgetAmount() {
    return budgetAmount;
  }

  public User getBudgetCreator() {
    return budgetCreator;
  }

  
}
