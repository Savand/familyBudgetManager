package org.andsav.family_budget_manager;

import static org.andsav.family_budget_manager.PreparedBudgetTestData.USER_1_2_BUDGET;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER1;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER2;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.model.enums.FundsFlowType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public interface PreparedFundsFlowTestData {
    public static LocalDateTime OPERATION_DATE_TIME__USER1_SALARY =
            LocalDateTime.of(LocalDate.of(2017, 01, 10), LocalTime.of(13, 0));
    public static final FundsFlow USER1_SALARY = new FundsFlow(100006, 15000, USER_1_2_BUDGET,
            "user1 salary", USER1, OPERATION_DATE_TIME__USER1_SALARY, FundsFlowType.INCOME);
    public static LocalDateTime OPERATION_DATE_TIME__USER2_EXPENSE =
            LocalDateTime.of(LocalDate.of(2017, 06, 10), LocalTime.of(10, 0));
    public static final FundsFlow USER2_EXPENSE = new FundsFlow(100014, -20, USER_1_2_BUDGET,
            "rubber duck", USER2, OPERATION_DATE_TIME__USER2_EXPENSE, FundsFlowType.HOUSHOLD);
    
    public static final ModelMatcher<FundsFlow> MATCHER = ModelMatcher.of(FundsFlow.class,
            (expected, actual) -> expected == actual ||
                            (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getAmount(), actual.getAmount())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getOperationDateTime(), actual.getOperationDateTime())
                            && Objects.equals(expected.getFundsFlowType(), actual.getFundsFlowType())
                    )
    );

}
