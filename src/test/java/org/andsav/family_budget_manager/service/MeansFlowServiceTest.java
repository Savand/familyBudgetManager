package org.andsav.family_budget_manager.service;

import static org.andsav.family_budget_manager.PreparedBudgetTestData.ADMIN_BUDGET;
import static org.andsav.family_budget_manager.PreparedMeansFlowTestData.USER1_SALARY;
import static org.andsav.family_budget_manager.PreparedMeansFlowTestData.USER2_EXPENSE;
import static org.andsav.family_budget_manager.PreparedUserTestData.ADMIN;
import static org.junit.Assert.assertEquals;

import org.andsav.family_budget_manager.model.MeansFlow;
import org.andsav.family_budget_manager.model.enums.MeansflowType;
import org.andsav.family_budget_manager.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql("classpath:db/populateDB.sql")
public class MeansFlowServiceTest {

    @Autowired
    MeansFlowService service;

    @Test
    public void testSave() {
        MeansFlow meansFlow = new MeansFlow(200, ADMIN_BUDGET, "beer, snacks", ADMIN,
                LocalDateTime.now(), MeansflowType.ENTERTAINMENT);
        service.save(meansFlow);
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
        MeansFlow meansFlowDb = service.get(100014);
        testUpdateMeansFlow(meansFlowDb);

        service.update(meansFlowDb);
        MeansFlow meansFlowDbUpdated = service.get(100014);
        testUpdateMeansFlow(USER2_EXPENSE);
        USER2_EXPENSE.setAmount(-40);

        assertEquals(USER2_EXPENSE, meansFlowDbUpdated);

    }

    @Test
    public void testGetbyBudgetId() {
        List<MeansFlow> meansFlowDbList = service.getbyBudgetId(100003);

        assertEquals(5, meansFlowDbList.size());
    }

    @Test
    public void testGetBetweenDateByBudgetId() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 10, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2017, 2, 10, 10, 0, 0);
        List<MeansFlow> meansFlowDbList =
                service.getBetweenDateByBudgetId(100003, startDate, endDate);

        assertEquals(4, meansFlowDbList.size());
    }

    @Test
    public void testGet() {
        MeansFlow actualMeansFlow = service.get(100006);
        assertEquals(USER1_SALARY, actualMeansFlow);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundGet() {
        service.get(111111);
    }


    private void testUpdateMeansFlow(MeansFlow meansFlow) {
        LocalDateTime updatedDateTime = meansFlow.getOperationDateTime();
        updatedDateTime.plusDays(5);

        meansFlow.setAmount(40);
        meansFlow.setCreationDate(updatedDateTime);
        meansFlow.setDescription("two ducks");
        meansFlow.setGoodsType(MeansflowType.ENTERTAINMENT);
    }

}
