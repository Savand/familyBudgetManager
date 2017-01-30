package org.andsav.familyBudgetManager.model;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.abstractentity.BaseEntity;
import org.andsav.familyBudgetManager.model.enums.MeansflowType;

public class MeansFlow extends BaseEntity {
  
  private Integer amount;
  
  private String description;

  private User byUser;
  
  private LocalDateTime operationDate;
  
  private MeansflowType goodsType;
  
  //constructors
  public MeansFlow() {}

  public MeansFlow(Integer id, Integer amount, String description, User byUser, LocalDateTime operationDate,
      MeansflowType goodsType) {
    super(id);
    this.amount = amount;
    this.description = description;
    this.byUser = byUser;
    this.operationDate = operationDate;
    this.goodsType = goodsType;
  }
  
  public MeansFlow(Integer amount, String description, User byUser, LocalDateTime operationDate,
      MeansflowType goodsType) {
    this(null, amount, description, byUser, operationDate, goodsType);
  }

  //getters and setters
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

  public MeansflowType getType() {
    return goodsType;
  }

  public void setGoodsType(MeansflowType goodsType) {
    this.goodsType = goodsType;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((amount == null) ? 0 : amount.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((goodsType == null) ? 0 : goodsType.hashCode());
    result = prime * result + ((operationDate == null) ? 0 : operationDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    MeansFlow other = (MeansFlow) obj;
    if (amount == null) {
      if (other.amount != null)
        return false;
    } else if (!amount.equals(other.amount))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (goodsType != other.goodsType)
      return false;
    if (operationDate == null) {
      if (other.operationDate != null)
        return false;
    } else if (!operationDate.equals(other.operationDate))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "MeansFlow [" + super.toString() + "amount=" + amount + ", description=" + description + ", operationDate="
        + operationDate + ", goodsType=" + goodsType + "]";
  }
  
  
  

}
