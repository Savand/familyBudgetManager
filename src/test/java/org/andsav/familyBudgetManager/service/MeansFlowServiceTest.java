package org.andsav.familyBudgetManager.service;

import static org.andsav.familyBudgetManager.PreparedBudgetTestData.ADMIN_BUDGET;
import static org.andsav.familyBudgetManager.PreparedUserTestData.ADMIN;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.andsav.familyBudgetManager.model.MeansFlow;
import org.andsav.familyBudgetManager.model.enums.MeansflowType;
import org.andsav.familyBudgetManager.util.DbPopulator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;;

@ContextConfiguration({
  "classpath:spring/spring-app.xml",
  "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MeansFlowServiceTest {
  
  @Autowired
  MeansFlowService service;
  
  @Autowired
  DbPopulator dbPopulator;
  
  @Before
  public void setUp(){
    dbPopulator.execute();
  }
  
  @Test
  public void testSave() {
    MeansFlow meansFlow = new MeansFlow(200, ADMIN_BUDGET, "beer, snacks", ADMIN, LocalDateTime.now(), MeansflowType.ENTERTAINMENT);
    service.save(meansFlow);
    assertEquals(6, service.getbyBudgetId(100003).size());
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSaveNull() {
    service.save(null);
  }

  @Test
  public void testDelete() {
    service.delete(100011);
    assertEquals(4, service.getbyBudgetId(100003).size());
  }

  @Test
  public void testUpdate() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetbyBudgetId() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetBetweenDateByBudgetId() {
    fail("Not yet implemented");
  }

  @Test
  public void testGet() {
    fail("Not yet implemented");
  }

}
