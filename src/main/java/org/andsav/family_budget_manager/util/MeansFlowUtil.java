package org.andsav.family_budget_manager.util;

import org.andsav.family_budget_manager.model.Meansflow;
import org.andsav.family_budget_manager.model.enums.MeansflowType;

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
    public static void signAmountRegardingType(Meansflow meansFlow) {
        boolean isIncome = meansFlow.getType() == MeansflowType.INCOME;
        if (!isIncome) {
            meansFlow.setAmount(meansFlow.getAmount() * -1);
        }
    }

}