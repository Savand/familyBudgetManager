package org.andsav.familyBudgetManager.model;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.abstractentity.BaseEntity;

public final class Income extends BaseEntity{

  private Integer incomeAmount;
  
  private String description;
  
  private User incomeByUser;
  
  private LocalDateTime incomeDate;
  
  //constructors
  public Income() {}

  public Income(Integer id, Integer incomeAmount, String description, User incomeByUser) {
    super(id);
    this.incomeAmount = incomeAmount;
    this.incomeByUser = incomeByUser;
    this.incomeDate = getInitializationDate();
    this.description = description;
  }
  
  public Income(Integer incomeAmount, String description, User incomeByUser) {
      this(null, incomeAmount, description, incomeByUser);
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  
  @Override
  public String toString() {
    return "Income [" +  super.toString() + "incomeAmount=" + incomeAmount + ", incomeByUser=" + incomeByUser + ", incomeDate=" + incomeDate
        + "]";
  }
  
  
}
