package org.andsav.familyBudgetManager.model;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.abstractentity.NamedEntity;

public final class Income extends NamedEntity{

  private Integer incomeAmount;
  
  private User incomeByUser;
  
  private LocalDateTime incomeDate;
  
  //constructors
  public Income() {}

  public Income(Integer id, String name, Integer incomeAmount, User incomeByUser, LocalDateTime incomeDate) {
    super(id, name);
    this.incomeAmount = incomeAmount;
    this.incomeByUser = incomeByUser;
    this.incomeDate = incomeDate;
  }

  public Income(String name, Integer incomeAmount, User incomeByUser, LocalDateTime incomeDate) {
    this(null, name, incomeAmount, incomeByUser, incomeDate);
  }

  //getters and setters
  public Integer getIncomeAmount() {
    return incomeAmount;
  }

  public void setIncomeAmount(Integer incomeAmount) {
    this.incomeAmount = incomeAmount;
  }

  public User getIncomeByUser() {
    return incomeByUser;
  }

  public void setIncomeByUser(User incomeByUser) {
    this.incomeByUser = incomeByUser;
  }

  public LocalDateTime getIncomeDate() {
    return incomeDate;
  }

  public void setIncomeDate(LocalDateTime incomeDate) {
    this.incomeDate = incomeDate;
  }

  
  @Override
  public String toString() {
    return "Income [" +  super.toString() + "incomeAmount=" + incomeAmount + ", incomeByUser=" + incomeByUser + ", incomeDate=" + incomeDate
        + "]";
  }
  
  
}
