package org.andsav.familyBudgetManager;

import org.andsav.familyBudgetManager.model.MeansFlow;
import org.andsav.familyBudgetManager.model.enums.MeansflowType;

import static org.andsav.familyBudgetManager.PreparedBudgetTestData.*;
import static org.andsav.familyBudgetManager.PreparedUserTestData.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface PreparedMeansFlowTestData {
  public static LocalDateTime OPERATION_DATE_TIME__USER1_SALARY = LocalDateTime.of(LocalDate.of(2017, 01, 10), LocalTime.of(10, 0));
  public static final MeansFlow USER1_SALARY = new MeansFlow(100006, 15000, USER_1_2_BUDGET, "user1 salary", USER1, OPERATION_DATE_TIME__USER1_SALARY, MeansflowType.INCOME);
  public static LocalDateTime OPERATION_DATE_TIME__USER2_EXPENSE = LocalDateTime.of(LocalDate.of(2017, 06, 10), LocalTime.of(10, 0));
  public static final MeansFlow USER2_EXPENSE = new MeansFlow(100014, -20, USER_1_2_BUDGET, "rubber duck", USER2, OPERATION_DATE_TIME__USER2_EXPENSE, MeansflowType.HOUSHOLD);

}
