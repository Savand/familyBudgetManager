package org.andsav.familyBudgetManager.model;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.andsav.familyBudgetManager.model.abstractentity.NamedEntity;
import org.andsav.familyBudgetManager.model.enums.Role;
import org.apache.commons.collections.CollectionUtils;

public final class User extends NamedEntity{
  
  private Set<Role> roles;
  
  private Byte[] userIcon;
  
  private String email;
  
  private String password;
  
  private List<Budget> budgets;
  
  private boolean enabled;

  //constructors
  public User() {}
  
  public User(String name, Byte[] userIcon, String email, String accountPassword, Role... roles) {
    this(null, name, userIcon, email, accountPassword, roles);
  }

  public User(Integer id, String name, Byte[] userIcon, String email, String accountPassword, Role... roles) {
    super(id, name);
    Set<Role> set = new HashSet<>();
    Collections.addAll(set, roles);
    setRoles(set);
    this.userIcon = userIcon;
    this.email = email;
    this.password = accountPassword;
    this.enabled = true;
  }

  //getters and setters
  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {
    this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
  }

  public Byte[] getUserIcon() {
    return userIcon;
  }

  public void setuserIcon(Byte[] userIcon) {
    this.userIcon = userIcon;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
    return "User [" + super.toString() + "email=" + email + ", enabled=" + enabled + "]";
  }

  
}
