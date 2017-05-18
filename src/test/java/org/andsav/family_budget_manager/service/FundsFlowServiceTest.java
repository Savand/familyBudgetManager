package org.andsav.family_budget_manager.service;

import static org.andsav.family_budget_manager.PreparedBudgetTestData.ADMIN_BUDGET;
import static org.andsav.family_budget_manager.PreparedFundsFlowTestData.USER1_SALARY;
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


    @Autowired
    protected FundsFlowService service;
    
    @Test
    public void testSave() {
        FundsFlow fundsFlow = new FundsFlow(200, ADMIN_BUDGET, "beer, snacks", ADMIN,
                LocalDateTime.now(), FundsFlowType.ENTERTAINMENT);
        service.save(fundsFlow);
        assertEquals(6, service.getbyBudgetId(100003).size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNull() {
        service.save(null);
    }

    @Test
    public void testDelete() {
        service.delete(100011);
        assertEquals(4, service.getbyBudgetId(100003).size());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() {
        service.delete(111111);
    }

    @Test
    public void testUpdate() {
        FundsFlow fundsFlow = service.get(100014);
        changeFundsFlowPosition(fundsFlow);

        service.update(fundsFlow);
        FundsFlow fundssFlowUpdated = service.get(100014);
        changeFundsFlowPosition(USER2_EXPENSE);
        USER2_EXPENSE.setAmount(-40);

        assertEquals(USER2_EXPENSE, fundssFlowUpdated);

    }

    @Test
    public void testGetbyBudgetId() {
        List<FundsFlow> fundsFlowList = service.getbyBudgetId(100003);

        assertEquals(5, fundsFlowList.size());
    }

    @Test
    public void testGetBetweenDateByBudgetId() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 10, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2017, 2, 10, 10, 0, 0);
        List<FundsFlow> fundsFlowList =
                service.getBetweenDateByBudgetId(100003, startDate, endDate);

        assertEquals(4, fundsFlowList.size());
    }

    @Test
    public void testGet() {
        FundsFlow actualFundsFlow = service.get(100006);
        assertEquals(USER1_SALARY, actualFundsFlow);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundGet() {
        service.get(111111);
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
