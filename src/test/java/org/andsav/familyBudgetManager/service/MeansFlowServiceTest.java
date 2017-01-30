package org.andsav.familyBudgetManager.service;

import static org.junit.Assert.fail;

import org.andsav.familyBudgetManager.util.DbPopulator;
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
    fail("Not yet implemented");
  }

  @Test
  public void testDelete() {
    fail("Not yet implemented");
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
