package org.andsav.familyBudgetManager.model;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.abstractentity.FinancialOperation;
import org.andsav.familyBudgetManager.model.enums.GoodsType;

public final class Outcome extends FinancialOperation{

  private GoodsType goodsType;

  
  public Outcome() { }

  public Outcome(Integer id, Integer amount, String description, User byUser, LocalDateTime operationDate, GoodsType goodsType) {
    super(id, amount, description, byUser, operationDate);
    this.goodsType = goodsType;
  }

  public Outcome(Integer amount, String description, User byUser, LocalDateTime operationDate, GoodsType goodsType) {
    this(null, amount, description, byUser, operationDate, goodsType);
  }

  
  public GoodsType getGoodsType() {
    return goodsType;
  }

  public void setGoodsType(GoodsType goodsType) {
    this.goodsType = goodsType;
  }

  @Override
  public String toString() {
    return "Outcome [" + super.toString() +"goodsType=" + goodsType + "]";
  }
  
  
}
