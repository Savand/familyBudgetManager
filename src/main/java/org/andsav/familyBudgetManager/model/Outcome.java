package org.andsav.familyBudgetManager.model;

import org.andsav.familyBudgetManager.model.abstractentity.DateInitEntity;
import org.andsav.familyBudgetManager.model.enums.GoodsType;

public class Outcome extends DateInitEntity{

	private GoodsType goodsType;
	private Integer goodsPurchaseValue;
	
	
	public Outcome() {}

	public Outcome(String description, GoodsType goodsType, Integer goodsPurchaseValue) {
		super(description);
		this.goodsType = goodsType;
		this.goodsPurchaseValue = goodsPurchaseValue;
	}

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
	
	@Override
	public String toString() {
		return "Outcome [goodsType=" + goodsType + ", goodsPurchaseValue=" + goodsPurchaseValue + "]";
	}
	
	
}
