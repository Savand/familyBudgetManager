package org.andsav.familyBudgetManager.model;

import java.util.List;
import java.util.Set;

import org.andsav.familyBudgetManager.model.abstractentity.NamedEntity;
import org.andsav.familyBudgetManager.model.enums.Role;

public final class User extends NamedEntity{
	
	private final Set<Role> roles;
	
	private final Byte[] img;
	
	private final String email;
	
	private final String accountPassword;
	
	private final List<Budget> budgets;

	
	public User(String name, Set<Role> roles, Byte[] img, String email, String accountPassword, List<Budget> budgets) {
		this(null, name, roles, img, email, accountPassword, budgets);
	}
	
	public User(Long id, String name, Set<Role> roles, Byte[] img, String email, String accountPassword, List<Budget> budgets) {
		super(id, name);
		this.roles = roles;
		this.img = img;
		this.email = email;
		this.accountPassword = accountPassword;
		this.budgets = budgets;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public Byte[] getImg() {
		return img;
	}

	public String getEmail() {
		return email;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public List<Budget> getBudgets() {
		return budgets;
	}
	
	
}
