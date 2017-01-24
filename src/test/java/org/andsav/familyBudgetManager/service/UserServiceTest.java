package org.andsav.familyBudgetManager.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.andsav.familyBudgetManager.model.User;
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
public class UserServiceTest {
  
  @Autowired
  UserService service;
  
  DbPopulator dbPopulator;
  
  @Before
  public void setUp(){
    dbPopulator.execute();
  }
  
  @Test
  public void testSave() {
    List<User> usersbyBudgetId = service.getUsersbyBudgetId(100004);
    System.out.println(usersbyBudgetId);
    
  }

  @Test
  public void testDelete() {
    fail("Not yet implemented"); //TODO
  }

  @Test
  public void testGet() {
    User user = service.get(100000);
    System.out.println(user);
  }

  @Test
  public void testGetByEmail() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetAll() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIdsbyBudgetId() {
    fail("Not yet implemented");
  }

}
