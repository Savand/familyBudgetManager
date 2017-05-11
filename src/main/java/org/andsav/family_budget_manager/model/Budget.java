package org.andsav.family_budget_manager.model;

import org.andsav.family_budget_manager.model.abstractentity.NamedEntity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Savand
 *
 */
@Entity
@Table(name = "BUDGETS")
@AttributeOverride(name = "name", column = @Column(name = "budget_name"))
@NamedQueries({@NamedQuery(name = Budget.DELETE, query = "DELETE FROM Budget b WHERE b.id=:id"),
        @NamedQuery(name = Budget.ALL_SORTED, query = "SELECT b FROM Budget b ORDER BY b.id")})
public class Budget extends NamedEntity {

    public static final String DELETE = "Budget.delete";
    public static final String ALL_SORTED = "Budget.getAllSorted";

    @Column(name = "budget_per_day", nullable = false)
    private int budgetPerDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "budget_creator_id")
    private User budgetCreator;

    @Column(nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "budget", cascade = CascadeType.ALL)
    private List<Meansflow> meansFlowList;


    public Budget() {}

    public Budget(Integer id, String budgetName, int budgetPerDay, User budgetCreator,
            String description) {
        super(id, budgetName);
        this.budgetPerDay = budgetPerDay;
        this.budgetCreator = budgetCreator;
        this.description = description;
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


    @Override
    public String toString() {
        return "Budget [" + super.toString() + ", budgetCreator=" + budgetCreator + "]";
    }



}
