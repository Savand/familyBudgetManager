package org.andsav.familyBudgetManager.model;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.abstractentity.NamedEntity;

public final class Income extends NamedEntity{

  private final Integer incomeAmount;
  
  private final User incomeByUser;
  
  private final LocalDateTime incomeDate;
  
  private final Budget budgetName;
  

  public Income(Long id, String name, Integer incomeAmount, User incomeByUser, LocalDateTime incomeDate, Budget budgetName) {
    super(id, name);
    this.incomeAmount = incomeAmount;
    this.incomeByUser = incomeByUser;
    this.incomeDate = incomeDate;
    this.budgetName = budgetName;
  }

  public Income(String name, Integer incomeAmount, User incomeByUser, LocalDateTime incomeDate, Budget budgetName) {
    this(null, name, incomeAmount, incomeByUser, incomeDate, budgetName);
  }

  public Integer getIncomeAmount() {
    return incomeAmount;
  }

  public User getIncomeByUser() {
    return incomeByUser;
  }

  public LocalDateTime getIncomeDate() {
    return incomeDate;
  }

  public Budget getBudgetName() {
    return budgetName;
  }

  
}
