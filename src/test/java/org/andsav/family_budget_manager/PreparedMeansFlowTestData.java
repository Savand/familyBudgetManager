package org.andsav.family_budget_manager;

import static org.andsav.family_budget_manager.PreparedBudgetTestData.USER_1_2_BUDGET;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER1;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER2;

import org.andsav.family_budget_manager.model.Meansflow;
import org.andsav.family_budget_manager.model.enums.MeansflowType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface PreparedMeansFlowTestData {
    public static LocalDateTime OPERATION_DATE_TIME__USER1_SALARY =
            LocalDateTime.of(LocalDate.of(2017, 01, 10), LocalTime.of(10, 0));
    public static final Meansflow USER1_SALARY = new Meansflow(100006, 15000, USER_1_2_BUDGET,
            "user1 salary", USER1, OPERATION_DATE_TIME__USER1_SALARY, MeansflowType.INCOME);
    public static LocalDateTime OPERATION_DATE_TIME__USER2_EXPENSE =
            LocalDateTime.of(LocalDate.of(2017, 06, 10), LocalTime.of(10, 0));
    public static final Meansflow USER2_EXPENSE = new Meansflow(100014, -20, USER_1_2_BUDGET,
            "rubber duck", USER2, OPERATION_DATE_TIME__USER2_EXPENSE, MeansflowType.HOUSHOLD);

}
