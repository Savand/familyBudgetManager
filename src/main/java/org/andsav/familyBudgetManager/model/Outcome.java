package org.andsav.familyBudgetManager.model;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.abstractentity.NamedEntity;
import org.andsav.familyBudgetManager.model.enums.GoodsType;

public final class Outcome extends NamedEntity{

  private final GoodsType goodsType;
  
  private final Integer goodsPurchaseValue;
  
  private final User outcomeByUser;
  
  private final LocalDateTime outcomeDate;
  
  private final Budget budgetName;

  
  public Outcome(Long id, String description, GoodsType goodsType, Integer goodsPurchaseValue, User outcomeByUser, LocalDateTime outcomeDate,
      Budget budgetName) {
    super(id, description);
    this.goodsType = goodsType;
    this.goodsPurchaseValue = goodsPurchaseValue;
    this.outcomeByUser = outcomeByUser;
    this.outcomeDate = outcomeDate;
    this.budgetName = budgetName;
  }

  public Outcome(String description, GoodsType goodsType, Integer goodsPurchaseValue, User outcomeByUser, LocalDateTime outcomeDate,
      Budget budgetName) {
    this(null, description, goodsType, goodsPurchaseValue, outcomeByUser, outcomeDate, budgetName);
  }


  public GoodsType getGoodsType() {
    return goodsType;
  }


  public Integer getGoodsPurchaseValue() {
    return goodsPurchaseValue;
  }


  public User getOutcomeByUser() {
    return outcomeByUser;
  }


  public LocalDateTime getOutcomeDate() {
    return outcomeDate;
  }


  public Budget getBudgetName() {
    return budgetName;
  }
  
  
}
