package org.andsav.family_budget_manager.util;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.model.enums.FundsFlowType;

/**
 * 
 * 
 * 
 * @author Andrii_Savka
 *
 */
public final class FundsFlowUtil {

  private FundsFlowUtil() {
  }

  /**
   * Changes amount sign to "-" in all cases except income type of means flow
   * 
   * @param fundsFlow
   */
  public static void signAmountRegardingType(FundsFlow fundsFlow) {
    boolean isIncome = fundsFlow.getFundsFlowType() == FundsFlowType.INCOME;
    if (!isIncome) {
      fundsFlow.setAmount(fundsFlow.getAmount() * -1);
    }
  }

}
