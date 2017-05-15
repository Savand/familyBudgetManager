package org.andsav.family_budget_manager.service;

import static org.andsav.family_budget_manager.PreparedBudgetTestData.ADMIN_BUDGET;
import static org.andsav.family_budget_manager.PreparedBudgetTestData.MATCHER;
import static org.andsav.family_budget_manager.PreparedBudgetTestData.USER_1_2_BUDGET;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER1;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.util.exception.NotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class BudgetServiceTest {
  
    private static final Log LOG = LogFactory.getLog(BudgetServiceTest.class);
    private static StringBuilder results = new StringBuilder();

    @Autowired
    protected BudgetService service;
    
    @Rule
    public Stopwatch stopwatch = new Stopwatch() {

        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append('\n');
            LOG.info(result + " ms\n");
        }
    };

    @AfterClass
    public static void printResults() {
        results = new StringBuilder("\n---------------------------------")
                .append("\nTest                 Duration, ms")
                .append("\n---------------------------------\n")
                .append(results)
                .append("---------------------------------\n");
        LOG.info(results.toString());
        results.setLength(0);
    }

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
    
    @Test
    public void testGetById() {
        MATCHER.assertEquals(ADMIN_BUDGET, service.get(100003));
    }
    
    @Test
    public void testGetAll() {
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_BUDGET, USER_1_2_BUDGET), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() {
        service.delete(111111);
    }


}
