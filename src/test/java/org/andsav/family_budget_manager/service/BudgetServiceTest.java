package org.andsav.family_budget_manager.service;

import static org.andsav.family_budget_manager.PreparedBudgetTestData.ADMIN_BUDGET;
import static org.andsav.family_budget_manager.PreparedBudgetTestData.MATCHER;
import static org.andsav.family_budget_manager.PreparedBudgetTestData.TEST_BUDGET;
import static org.andsav.family_budget_manager.PreparedBudgetTestData.USER_1_2_BUDGET;
import static org.andsav.family_budget_manager.PreparedUserTestData.ADMIN;
import static org.andsav.family_budget_manager.PreparedUserTestData.ADMIN_ID;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER1;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER1_ID;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER2;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER2_ID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.model.User;
import org.andsav.family_budget_manager.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class BudgetServiceTest extends AbstractServiceTest {

    @Autowired
    protected BudgetService service;

    @Autowired
    protected UserService userService;

    @Test
    public void testCreate() {
        Budget savedTestBudget = service.create(TEST_BUDGET, USER1_ID);
        TEST_BUDGET.setId(savedTestBudget.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(USER_1_2_BUDGET, TEST_BUDGET),
                service.getAll(USER1.getId()));
    }

    @Test
    public void testIsContributor() {
        assertTrue(service.isContributor(USER_1_2_BUDGET.getId(), USER1_ID));
        assertTrue(service.isContributor(USER_1_2_BUDGET.getId(), USER2_ID));
        assertFalse(service.isContributor(USER_1_2_BUDGET.getId(), ADMIN_ID));
    }

    @Test
    public void testAddContributorToBudget() {
        Budget budgetfromDb = service.getBudgetWithContributors(USER_1_2_BUDGET.getId());
        service.addContributorToBudget(budgetfromDb, ADMIN_ID, USER1_ID);

        Set<User> budgetContributors =
                service.getBudgetWithContributors(USER_1_2_BUDGET.getId()).getBudgetContributors();
        List<User> usersForTest = Arrays.asList(ADMIN, USER1, USER2);
        assertTrue(budgetContributors.containsAll(usersForTest));
        assertTrue(usersForTest.containsAll(budgetContributors));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNull() {
        service.create(null, 0);
    }

    @Test
    public void testUpdate() {
        Budget budgetForUpdate = service.get(ADMIN_BUDGET.getId());
        budgetForUpdate.setBudgetPerDay(300);
        service.update(budgetForUpdate, ADMIN_ID);
        ADMIN_BUDGET.setBudgetPerDay(300);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_BUDGET), service.getAll(ADMIN_ID));
    }

    @Test
    public void testDelete() {
        service.delete(ADMIN_BUDGET.getId(), ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_1_2_BUDGET), service.getAll(USER1_ID));
    }

    @Test
    public void testGet() {
        MATCHER.assertEquals(ADMIN_BUDGET, service.get(ADMIN_BUDGET.getId()));
    }

    @Test
    public void testGetAll() {
        MATCHER.assertCollectionEquals(Arrays.asList(USER_1_2_BUDGET),
                service.getAll(USER1.getId()));
    }


    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() {
        service.delete(111111, 1);
    }

}
