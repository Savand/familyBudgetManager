package org.andsav.familyBudgetManager.model;

import java.time.LocalDateTime;

import org.andsav.familyBudgetManager.model.abstractentity.BaseEntity;
import org.andsav.familyBudgetManager.model.enums.MeansflowType;

public class MeansFlow extends BaseEntity {
  
  private Integer amount;
  
  private String description;

  private User byUser;
  
  private LocalDateTime operationDate;
  
  private MeansflowType goodsType;

}
