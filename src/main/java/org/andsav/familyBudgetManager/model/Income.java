package org.andsav.familyBudgetManager.model;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.abstractentity.FinancialOperation;

public final class Income extends FinancialOperation{

    public Income() {}

    public Income(Integer id, Integer amount, String description, User byUser, LocalDateTime operationDate) {
      super(id, amount, description, byUser, operationDate);
    }

    public Income(Integer amount, String description, User byUser, LocalDateTime operationDate) {
      super(amount, description, byUser, operationDate);
    }

    @Override
    public String toString() {
      return "Income [" + super.toString() + "]";
    }
    
    
}
