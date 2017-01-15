package org.andsav.familyBudgetManager.model;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.abstractentity.BaseEntity;
import org.andsav.familyBudgetManager.model.enums.GoodsType;

public final class Outcome extends BaseEntity{

  private GoodsType goodsType;
  
  private Integer goodsPurchaseValue;
  
  private String description;
  
  private User outcomeByUser;
  
  private LocalDateTime outcomeDate;

  
  public Outcome() {}

  public Outcome(Integer id, String description, GoodsType goodsType, Integer goodsPurchaseValue, User outcomeByUser, LocalDateTime outcomeDate) {
    super(id);
    this.goodsType = goodsType;
    this.description = description;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Outcome [" + super.toString() + "goodsType=" + goodsType + ", goodsPurchaseValue=" + goodsPurchaseValue + ", outcomeByUser="
        + outcomeByUser + ", outcomeDate=" + outcomeDate + "]";
  }

  
}
