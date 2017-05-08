package org.andsav.family_budget_manager.model;

import org.andsav.family_budget_manager.model.abstractentity.NamedEntity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Savand
 *
 */
@Entity
@Table(name = "BUGETS")
public class Budget extends NamedEntity {

    @Column(nullable = false)
    private int budgetPerDay;

    @ManyToOne(fetch = FetchType.LAZY)
    private User budgetOwner;

    @Column(nullable = false)
    private String description;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MeansFlow> meansFlowList;


    public Budget() {}

    public Budget(Integer id, String budgetName, int budgetPerDay,
            User budgetCreator, String description) {
        super(id, budgetName);
        this.budgetPerDay = budgetPerDay;
        this.budgetOwner = budgetCreator;
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
        return budgetOwner;
    }

    public void setBudgetCreator(User budgetCreator) {
        this.budgetOwner = budgetCreator;
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
        return "Budget [" + super.toString() + ", budgetCreator="
                + budgetOwner + "]";
    }



}
