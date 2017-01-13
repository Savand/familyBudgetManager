package org.andsav.familyBudgetManager.model;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.abstractentity.NamedEntity;
import org.andsav.familyBudgetManager.model.enums.GoodsType;

public final class Outcome extends NamedEntity{

  private GoodsType goodsType;
  
  private Integer goodsPurchaseValue;
  
  private User outcomeByUser;
  
  private LocalDateTime outcomeDate;

  
  public Outcome() {}

  public Outcome(Integer id, String description, GoodsType goodsType, Integer goodsPurchaseValue, User outcomeByUser, LocalDateTime outcomeDate) {
    super(id, description);
    this.goodsType = goodsType;
    this.goodsPurchaseValue = goodsPurchaseValue;
    this.outcomeByUser = outcomeByUser;
    this.outcomeDate = outcomeDate;
  }

  public Outcome(String description, GoodsType goodsType, Integer goodsPurchaseValue, User outcomeByUser, LocalDateTime outcomeDate) {
    this(null, description, goodsType, goodsPurchaseValue, outcomeByUser, outcomeDate);
  }
  
  //getters and setters
  public GoodsType getGoodsType() {
    return goodsType;
  }

  public void setGoodsType(GoodsType goodsType) {
    this.goodsType = goodsType;
  }

  public Integer getGoodsPurchaseValue() {
    return goodsPurchaseValue;
  }

  public void setGoodsPurchaseValue(Integer goodsPurchaseValue) {
    this.goodsPurchaseValue = goodsPurchaseValue;
  }

  public User getOutcomeByUser() {
    return outcomeByUser;
  }

  public void setOutcomeByUser(User outcomeByUser) {
    this.outcomeByUser = outcomeByUser;
  }

  public LocalDateTime getOutcomeDate() {
    return outcomeDate;
  }

  public void setOutcomeDate(LocalDateTime outcomeDate) {
    this.outcomeDate = outcomeDate;
  }

  @Override
  public String toString() {
    return "Outcome [" + super.toString() + "goodsType=" + goodsType + ", goodsPurchaseValue=" + goodsPurchaseValue + ", outcomeByUser="
        + outcomeByUser + ", outcomeDate=" + outcomeDate + "]";
  }

  
}
