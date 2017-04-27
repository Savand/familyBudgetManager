package org.andsav.family_budget_manager.model;

import org.andsav.family_budget_manager.model.abstractentity.NamedEntity;
import org.andsav.family_budget_manager.model.enums.Role;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "USERS",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "unique_email")})
public class User extends NamedEntity {

    @Column
    protected Byte[] userIcon;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    @NotEmpty
    @Length(min = 5)
    protected String password;

    @ManyToMany(fetch = FetchType.EAGER)
    protected List<Budget> budgets;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<Role> roles;

    @Column(nullable = false)
    protected boolean enabled;

    public User() {}

    public User(String name, Byte[] userIcon, String email, String accountPassword, Role... roles) {
        this(null, name, userIcon, email, accountPassword, roles);
    }

    public User(Integer id, String name, Byte[] userIcon, String email, String accountPassword,
            Role... roles) {
        super(id, name);
        Set<Role> set = new HashSet<>();
        Collections.addAll(set, roles);
        setRoles(set);
        this.userIcon = userIcon;
        this.email = email;
        this.password = accountPassword;
        this.enabled = true;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles =
                CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
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
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + (enabled ? 1231 : 1237);
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + Arrays.hashCode(userIcon);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (enabled != other.enabled)
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (!Arrays.equals(userIcon, other.userIcon))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [" + super.toString() + "email=" + email + ", enabled=" + enabled + "]";
    }

}
