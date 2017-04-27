package org.andsav.family_budget_manager;

import org.andsav.family_budget_manager.model.Budget;

public interface PreparedBudgetTestData {

    public static final Budget ADMIN_BUDGET = new Budget(100003, "admin budget", 600, 50000, null,
            "budget control of admins money", null, null);
    public static final Budget USER_1_2_BUDGET = new Budget(100004, "users budget", 400, 20000,
            null, "budget control of users family money", null, null);

}
