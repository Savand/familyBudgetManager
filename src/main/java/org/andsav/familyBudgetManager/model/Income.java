package org.andsav.familyBudgetManager.model;

import org.andsav.familyBudgetManager.model.abstractentity.DateInitEntity;

public final class Income extends DateInitEntity{

	private Integer incomeAmount;

	public Income() {}

	public Income(String name, Integer incomeAmount) {
		super(name);
		this.incomeAmount = incomeAmount;
	}

	public Integer getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(Integer incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	
	
}
