package org.andsav.family_budget_manager;

import static org.andsav.family_budget_manager.PreparedUserTestData.USER1;

import org.andsav.family_budget_manager.model.Budget;

import java.util.Objects;

public interface PreparedBudgetTestData {

    public static final Budget ADMIN_BUDGET = new Budget(100003, "admins budget", 600, null,
            "budget control of admins money");
    
    public static final Integer ADMIN_BUDGET__ID = ADMIN_BUDGET.getId();
    
    public static final Budget USER_1_2_BUDGET = new Budget(100004, "users budget", 400, 
            null, "budget control of users family money");
   
    public static final Integer USER_1_2_BUDGET__ID = USER_1_2_BUDGET.getId();
    
    public static final Budget TEST_BUDGET =
            new Budget("new budget", 600, 50000, USER1, "new budget just for testing");
    
    public static final ModelMatcher<Budget> MATCHER = ModelMatcher.of(Budget.class,
            (expected, actual) -> expected == actual ||
                            (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getBudgetPerDay(), actual.getBudgetPerDay())
                    )
    );

}
