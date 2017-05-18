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
public final class MeansFlowUtil {

    private MeansFlowUtil() {}

    /**
     * Changes amount sign to "-" in all cases except income type of means flow
     * 
     * @param meansFlow
     */
    public static void signAmountRegardingType(FundsFlow meansFlow) {
        boolean isIncome = meansFlow.getType() == FundsFlowType.INCOME;
        if (!isIncome) {
            meansFlow.setAmount(meansFlow.getAmount() * -1);
        }
    }

}
