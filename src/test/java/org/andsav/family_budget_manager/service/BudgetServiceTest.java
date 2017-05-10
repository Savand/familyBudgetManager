package org.andsav.family_budget_manager.service;

import static org.andsav.family_budget_manager.PreparedBudgetTestData.ADMIN_BUDGET;
import static org.andsav.family_budget_manager.PreparedBudgetTestData.MATCHER;
import static org.andsav.family_budget_manager.PreparedBudgetTestData.USER_1_2_BUDGET;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER1;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql("classpath:db/populateDB.sql")
public class BudgetServiceTest {

    @Autowired
    BudgetService service;

    @Test
    public void testSave() {
        Budget newTestBudget =
                new Budget("new budget", 600, 50000, USER1, "new budget just for testing");
        Budget savedTestBudget = service.save(newTestBudget);
        newTestBudget.setId(savedTestBudget.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_BUDGET, USER_1_2_BUDGET, newTestBudget), service.getAll());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNull() {
        service.save(null);
    }

    @Test
    public void testUpdate() {
        Budget budget = service.get(100004);
        budget.setBudgetPerDay(600);
        service.update(budget);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_BUDGET, budget), service.getAll());
    }

    @Test
    public void testDelete() {
        service.delete(100004);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_BUDGET), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() {
        service.delete(111111);
    }

    @Test
    public void testGetbyUserId() {
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_BUDGET), service.getbyUserId(100000));
    }

}
