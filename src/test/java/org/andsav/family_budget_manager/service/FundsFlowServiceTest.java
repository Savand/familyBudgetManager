package org.andsav.family_budget_manager.service;

import static org.andsav.family_budget_manager.PreparedBudgetTestData.ADMIN_BUDGET;
import static org.andsav.family_budget_manager.PreparedBudgetTestData.ADMIN_BUDGET__ID;
import static org.andsav.family_budget_manager.PreparedBudgetTestData.USER_1_2_BUDGET__ID;
import static org.andsav.family_budget_manager.PreparedFundsFlowTestData.*;
import static org.andsav.family_budget_manager.PreparedFundsFlowTestData.USER2_EXPENSE;
import static org.andsav.family_budget_manager.PreparedUserTestData.ADMIN;
import static org.junit.Assert.assertEquals;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.model.enums.FundsFlowType;
import org.andsav.family_budget_manager.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;;

public class FundsFlowServiceTest extends AbstractServiceTest {

  public static final int NOT_EXISTING_IREM__ID = 111111;
  public static final int ADMINS_FUNDS_FLOW__ID = 100011;
  public static final int USER2_FUNDS_FLOW__ID = 100014;
  public static final int USER_FUNDS_FLOW_SALARY__ID = 100006;

  @Autowired
  protected FundsFlowService service;

  @Test
  public void testSave() {
    FundsFlow fundsFlow = new FundsFlow(200, ADMIN_BUDGET, "beer, snacks", ADMIN, LocalDateTime.now(),
        FundsFlowType.ENTERTAINMENT);
    service.save(fundsFlow, ADMIN_BUDGET__ID);
    assertEquals(6, service.getAll(ADMIN_BUDGET__ID).size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveNull() {
    service.save(null, 0);
  }

  @Test
  public void testDelete() {
    service.delete(ADMINS_FUNDS_FLOW__ID, ADMIN_BUDGET__ID);
    assertEquals(4, service.getAll(ADMIN_BUDGET__ID).size());
  }

  @Test(expected = NotFoundException.class)
  public void testNotFoundMeansflowDelete() {
    service.delete(NOT_EXISTING_IREM__ID, ADMIN_BUDGET__ID);
  }

  @Test(expected = NotFoundException.class)
  public void testNotFoundInBudgetDelete() {
    service.delete(USER2_FUNDS_FLOW__ID, NOT_EXISTING_IREM__ID);
  }

  @Test
  public void testUpdate() {
    FundsFlow fundsFlow = service.get(USER2_FUNDS_FLOW__ID, USER_1_2_BUDGET__ID);
    changeFundsFlowPosition(fundsFlow);

    service.update(fundsFlow, USER_1_2_BUDGET__ID);
    FundsFlow fundsFlowUpdated = service.get(USER2_FUNDS_FLOW__ID, USER_1_2_BUDGET__ID);
    changeFundsFlowPosition(USER2_EXPENSE);
    USER2_EXPENSE.setAmount(-40);

    assertEquals(USER2_EXPENSE, fundsFlowUpdated);

  }

  @Test
  public void testGetbyBudgetId() {
    List<FundsFlow> fundsFlowList = service.getAll(ADMIN_BUDGET__ID);

    assertEquals(5, fundsFlowList.size());
  }

  @Test
  public void testGetBetweenDateByBudgetId() {
    LocalDateTime startDate = LocalDateTime.of(2017, 1, 10, 10, 0, 0);
    LocalDateTime endDate = LocalDateTime.of(2017, 2, 10, 10, 0, 0);
    List<FundsFlow> fundsFlowList = service.getBetweenDateByBudgetId(100003, startDate, endDate);

    assertEquals(4, fundsFlowList.size());
  }

  @Test
  public void testGet() {
    FundsFlow actualFundsFlow = service.get(USER_FUNDS_FLOW_SALARY__ID, USER_1_2_BUDGET__ID);
    MATCHER.assertEquals(USER1_SALARY, actualFundsFlow);
  }

  @Test(expected = NotFoundException.class)
  public void testNotFoundGet() {
    service.get(NOT_EXISTING_IREM__ID, USER_1_2_BUDGET__ID);
  }

  @Test(expected = NotFoundException.class)
  public void testNotFoundGetInBudget() {
    service.get(USER2_FUNDS_FLOW__ID, ADMIN_BUDGET__ID);
  }

  private void changeFundsFlowPosition(FundsFlow fundsFlowPosition) {
    LocalDateTime updatedDateTime = fundsFlowPosition.getOperationDateTime();
    updatedDateTime.plusDays(5);

    fundsFlowPosition.setAmount(40);
    fundsFlowPosition.setCreationDate(updatedDateTime);
    fundsFlowPosition.setDescription("two ducks");
    fundsFlowPosition.setFundsFlowType(FundsFlowType.ENTERTAINMENT);
  }

}
