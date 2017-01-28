package org.andsav.familyBudgetManager.service;

import static org.andsav.familyBudgetManager.PreparedBudgetTestData.ADMIN_BUDGET;
import static org.andsav.familyBudgetManager.PreparedBudgetTestData.USER_1_2_BUDGET;
import static org.andsav.familyBudgetManager.PreparedUserTestData.USER1;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.andsav.familyBudgetManager.model.Budget;
import org.andsav.familyBudgetManager.util.DbPopulator;
import org.andsav.familyBudgetManager.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({
  "classpath:spring/spring-app.xml",
  "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class BudgetServiceTest {
  
  @Autowired
  BudgetService service;
  
  @Autowired
  DbPopulator dbPopulator;
  
  @Before
  public void setUp(){
    dbPopulator.execute();
  }
  
  @Test
  public void testSave() {
    Budget newTestBudget = new Budget("new budget", 600, 50000, USER1, "new budget just for testing", null, null);

    Budget savedTestBudget = service.save(newTestBudget);
    
    newTestBudget.setId(savedTestBudget.getId());
    
    assertThat(service.getAll(), is(Arrays.asList(ADMIN_BUDGET, USER_1_2_BUDGET, newTestBudget)));
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSaveNull() {
    service.save(null);
  }
  
  @Test
  public void testUpdate() {
    Budget budget = service.get(100004);
    
    budget.setBudgetPerDay(600);
    
    service.update(budget);
    
    assertThat(service.getAll(), is(Arrays.asList(ADMIN_BUDGET, budget)));
  }

  @Test
  public void testDelete() {
    service.delete(100004);
    assertThat(service.getAll(), is(Arrays.asList(ADMIN_BUDGET)));
  }
  
  @Test(expected = NotFoundException.class)
  public void testNotFoundDelete() {
    service.delete(111111);
  }

  @Test
  public void testGetbyUserId() {
    assertThat(service.getbyUserId(100000), is(Arrays.asList(ADMIN_BUDGET)));
  }

}
