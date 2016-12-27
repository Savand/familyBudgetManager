package org.andsav.familyBudgetManager.model.abstractentity;

import java.util.Date;

public abstract class DateInitEntity extends NamedEntity {

	Date initializationDate;

	public DateInitEntity() {}

	public DateInitEntity(String name) {
		super(name);
		this.initializationDate = new Date();
	}

	public Date getInitializationDate() {
		return initializationDate;
	}

	public void setInitializationDate(Date initializationDate) {
		this.initializationDate = initializationDate;
	}

	@Override
	public String toString() {
		return "InitialDatedEntity [initializationDate=" + initializationDate + "]";
	}
	
	
	
}
