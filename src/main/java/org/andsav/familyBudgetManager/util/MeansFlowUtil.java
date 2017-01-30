package org.andsav.familyBudgetManager.util;

import org.andsav.familyBudgetManager.model.MeansFlow;
import org.andsav.familyBudgetManager.model.enums.MeansflowType;

public class MeansFlowUtil {
  //change amount sign to "-" if it is not income type of means flow
  public static void signAmountRegardingType(MeansFlow meansFlow) {
    boolean isIncome = meansFlow.getType() == MeansflowType.INCOME;
    if(!isIncome)
      meansFlow.setAmount(meansFlow.getAmount() * -1);
  }

}
