package org.andsav.family_budget_manager.model;

import org.andsav.family_budget_manager.model.abstractentity.NamedEntity;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Savand
 *
 */
@Entity
@Table(name = "BUDGETS")
@AttributeOverride(name = "name", column = @Column(name = "budget_name"))
@NamedQueries(@NamedQuery(name = Budget.DELETE, query = "DELETE FROM Budget b WHERE b.id= :id AND b.budgetCreator.id= :userId"))
public class Budget extends NamedEntity {

    public static final String DELETE = "Budget.delete";
    public static final String ALL_SORTED = "Budget.getAllSorted";

    @Column(name = "budget_per_day", nullable = false)
    private int budgetPerDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_creator_id")
    private User budgetCreator;

    @Column(nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "budget")
    @BatchSize(size = 200)
    private List<FundsFlow> meansFlowList;
    

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "budgets_users",
            uniqueConstraints = @UniqueConstraint(columnNames = {"budget_id", "user_id"}),
            joinColumns = @JoinColumn(name = "budget_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> budgetContributors;


    public Budget() {}

    public Budget(Integer id, String budgetName, int budgetPerDay, User budgetCreator,
            String description) {
        super(id, budgetName);
        this.budgetPerDay = budgetPerDay;
        this.budgetCreator = budgetCreator;
        this.description = description;
        this.budgetContributors = new HashSet<>();
    }

    public Budget(String budgetName, int budgetPerDay, Integer budgetAmount, User budgetCreator,
            String description) {
        this(null, budgetName, budgetPerDay, budgetCreator, description);
    }

    public int getBudgetPerDay() {
        return budgetPerDay;
    }

    public User getBudgetCreator() {
        return budgetCreator;
    }

    public void setBudgetCreator(User budgetCreator) {
        this.budgetCreator = budgetCreator;
    }

    public void setBudgetPerDay(int budgetPerDay) {
        this.budgetPerDay = budgetPerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<User> getBudgetContributors() {
        return budgetContributors;
    }
    
    public void addContributor(User contributor) {
        budgetContributors.add(contributor);
    }

    @Override
    public String toString() {
        return "Budget [" + super.toString() + ", description=" + description + ", budgetPerDay=" + budgetPerDay + "]";
    }





}
