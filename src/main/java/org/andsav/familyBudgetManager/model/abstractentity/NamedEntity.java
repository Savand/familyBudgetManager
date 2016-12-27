package org.andsav.familyBudgetManager.model.abstractentity;

public abstract class NamedEntity extends BaseEntity{
	
	private String name;

	public NamedEntity() {}

	public NamedEntity(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NamedEntity [name=" + name + "]";
	}
	

}
