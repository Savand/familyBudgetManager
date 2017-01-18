package org.andsav.familyBudgetManager.model.abstractentity;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.User;
import org.andsav.familyBudgetManager.model.enums.GoodsType;

public abstract class FinancialOperation extends BaseEntity{

  private Integer amount;
  
  private String description;

  private User byUser;
  
  private LocalDateTime operationDate;
  
//TODO finish class
}
