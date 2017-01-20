package org.andsav.familyBudgetManager.model.abstractentity;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.User;

public abstract class FinancialOperation extends BaseEntity{

  private Integer amount;
  
  private String description;

  private User byUser;
  
  private LocalDateTime operationDate;

  public FinancialOperation() {
  }

  public FinancialOperation(Integer id, Integer amount, String description, User byUser, LocalDateTime operationDate) {
    super(id);
    this.amount = amount;
    this.description = description;
    this.byUser = byUser;
    this.operationDate = operationDate;
  }
  
  public FinancialOperation(Integer amount, String description, User byUser, LocalDateTime operationDate) {
    this(null, amount, description, byUser, operationDate);
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getByUser() {
    return byUser;
  }

  public void setByUser(User byUser) {
    this.byUser = byUser;
  }

  public LocalDateTime getOperationDate() {
    return operationDate;
  }

  public void setOperationDate(LocalDateTime operationDate) {
    this.operationDate = operationDate;
  }

  
  @Override
  public String toString() {
    return "amount=" + amount + ", description=" + description + ", byUser=" + byUser
        + ", operationDate=" + operationDate + "]";
  }
  
}
