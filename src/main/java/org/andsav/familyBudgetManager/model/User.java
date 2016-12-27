package org.andsav.familyBudgetManager.model;

import java.util.List;
import java.util.Set;

import org.andsav.familyBudgetManager.model.abstractentity.DateInitEntity;
import org.andsav.familyBudgetManager.model.enums.Role;

public final class User extends DateInitEntity{
	
	private Set<Role> roles;
	private String email;
	private List<Outcome> outcomes;
	private List<Income> incomes;
	
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Outcome> getOutcomes() {
		return outcomes;
	}
	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}
	public List<Income> getIncomes() {
		return incomes;
	}
	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}
	
	
	@Override
	public String toString() {
		return "User [roles=" + roles + ", email=" + email + ", outcomes=" + outcomes + ", incomes=" + incomes + "]";
	}
	
	
}
