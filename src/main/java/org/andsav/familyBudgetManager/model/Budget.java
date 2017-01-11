package org.andsav.familyBudgetManager.model;

import java.util.List;

import org.andsav.familyBudgetManager.model.abstractentity.NamedEntity;

/**
 * @author Savand
 *
 */
public class Budget extends NamedEntity {
  
  private int budgetPerDay;
  
  private Integer budgetAmount;
  
  private User budgetCreator;
  
  private List<Income> incomesList;
  
  private List<Outcome> outcomeList;
  
  
  //constructors
  public Budget() {}

  public Budget(Long id, String budgetName, int budgetPerDay, Integer budgetAmount, User budgetCreator, List<Income> incomesList, List<Outcome> outcomeList) {
    super(id, budgetName);
    this.budgetPerDay = budgetPerDay;
    this.budgetAmount = budgetAmount;
    this.budgetCreator = budgetCreator;
    this.incomesList = incomesList;
    this.outcomeList = outcomeList;
  }
  
  public Budget(String budgetName, int budgetPerDay, Integer budgetAmount, User budgetCreator, List<Income> incomesList, List<Outcome> outcomeList) {
    this(null, budgetName, budgetPerDay, budgetAmount, budgetCreator, incomesList, outcomeList);
  }

  //getters and setters
  public int getBudgetPerDay() {
    return budgetPerDay;
  }

  public Integer getBudgetAmount() {
    return budgetAmount;
  }

  public void setBudgetAmount(Integer budgetAmount) {
    this.budgetAmount = budgetAmount;
  }

  public User getBudgetCreator() {
    return budgetCreator;
  }

  public void setBudgetCreator(User budgetCreator) {
    this.budgetCreator = budgetCreator;
  }

  public List<Income> getIncomesList() {
    return incomesList;
  }

  public void setIncomesList(List<Income> incomesList) {
    this.incomesList = incomesList;
  }

  public List<Outcome> getOutcomeList() {
    return outcomeList;
  }

  public void setOutcomeList(List<Outcome> outcomeList) {
    this.outcomeList = outcomeList;
  }

  public void setBudgetPerDay(int budgetPerDay) {
    this.budgetPerDay = budgetPerDay;
  }
  

  @Override
  public String toString() {
    return "Budget [" + super.toString() + "budgetAmount=" + budgetAmount + ", budgetCreator=" + budgetCreator + "]";
  }

  
}
