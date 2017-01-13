package org.andsav.familyBudgetManager.model;

import java.util.List;
import java.util.Set;

import org.andsav.familyBudgetManager.model.abstractentity.NamedEntity;
import org.andsav.familyBudgetManager.model.enums.Role;

public final class User extends NamedEntity{
  
  private Set<Role> roles;
  
  private Byte[] img;
  
  private String email;
  
  private String accountPassword;
  
  private List<Budget> budgets;
  
  private boolean enabled;

  //constructors
  public User() {}
  
  public User(String name, Set<Role> roles, Byte[] img, String email, String accountPassword, List<Budget> budgets) {
    this(null, name, roles, img, email, accountPassword, budgets);
  }

  public User(Integer id, String name, Set<Role> roles, Byte[] img, String email, String accountPassword, List<Budget> budgets) {
    super(id, name);
    this.roles = roles;
    this.img = img;
    this.email = email;
    this.accountPassword = accountPassword;
    this.budgets = budgets;
    this.enabled = true;
  }

  //getters and setters
  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public Byte[] getImg() {
    return img;
  }

  public void setImg(Byte[] img) {
    this.img = img;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAccountPassword() {
    return accountPassword;
  }

  public void setAccountPassword(String accountPassword) {
    this.accountPassword = accountPassword;
  }

  public List<Budget> getBudgets() {
    return budgets;
  }

  public void setBudgets(List<Budget> budgets) {
    this.budgets = budgets;
  }
  
  public boolean isEnabled() {
	return enabled;
  }

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

@Override
  public String toString() {
    return "User [" + super.toString() + "email=" + email + "enabled=" + enabled + "]";
  }

  
}
