package org.andsav.family_budget_manager.model;

import org.andsav.family_budget_manager.model.abstractentity.NamedEntity;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

/**
 * @author Savand
 *
 */
public class Budget extends NamedEntity {

    protected int budgetPerDay;

    protected Integer budgetAmount;

    protected User budgetOwner;

    protected String description;

    @ManyToMany(fetch = FetchType.LAZY)
    protected List<User> contributors;

    protected List<MeansFlow> meansFlowList;


    public Budget() {}

    public Budget(Integer id, String budgetName, int budgetPerDay, Integer budgetAmount,
            User budgetCreator, String description, List<MeansFlow> meansFlowList,
            List<User> contributors) {
        super(id, budgetName);
        this.budgetPerDay = budgetPerDay;
        this.budgetAmount = budgetAmount;
        this.budgetOwner = budgetCreator;
        this.description = description;
        this.meansFlowList = meansFlowList;
        this.contributors = contributors;
    }

    public Budget(String budgetName, int budgetPerDay, Integer budgetAmount, User budgetCreator,
            String description, List<MeansFlow> meansFlowList, List<User> contributors) {
        this(null, budgetName, budgetPerDay, budgetAmount, budgetCreator, description,
                meansFlowList, contributors);
    }

    public int getBudgetPerDay() {
        return budgetPerDay;
    }

    public Integer getInitialBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Integer budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public User getBudgetCreator() {
        return budgetOwner;
    }

    public void setBudgetCreator(User budgetCreator) {
        this.budgetOwner = budgetCreator;
    }

    public List<MeansFlow> getMeansFlowList() {
        return meansFlowList;
    }

    public void setMeansFlowList(final List<MeansFlow> meansFlowList) {
        this.meansFlowList = meansFlowList;
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

    public List<User> getContributors() {
        return contributors;
    }

    public void setContributors(List<User> contributors) {
        this.contributors = contributors;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((budgetAmount == null) ? 0 : budgetAmount.hashCode());
        result = prime * result + budgetPerDay;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        Budget other = (Budget) obj;
        if (budgetAmount == null) {
            if (other.budgetAmount != null)
                return false;
        } else if (!budgetAmount.equals(other.budgetAmount))
            return false;
        if (budgetPerDay != other.budgetPerDay)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Budget [" + super.toString() + "budgetAmount=" + budgetAmount + ", budgetCreator="
                + budgetOwner + "]";
    }



}
