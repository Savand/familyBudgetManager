package org.andsav.familyBudgetManager.service;


import static org.andsav.familyBudgetManager.PreparedUserTestData.ADMIN;
import static org.andsav.familyBudgetManager.PreparedUserTestData.USER1;
import static org.andsav.familyBudgetManager.PreparedUserTestData.USER2;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.andsav.familyBudgetManager.model.User;
import org.andsav.familyBudgetManager.model.enums.Role;
import org.andsav.familyBudgetManager.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration({
  "classpath:spring/spring-app.xml",
  "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql("classpath:db/populateDB.sql")
public class UserServiceTest {
  
  @Autowired
  UserService service;
  
  @Test
  public void testSave() {
    User newTestUser = new User("new User", null, "newUser@gmail.com", "password", Role.USER);

    User savedTestUser = service.save(newTestUser);
    
    newTestUser.setId(savedTestUser.getId());
    
    assertThat(service.getAll(), is(Arrays.asList(ADMIN, newTestUser, USER1, USER2)));
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSaveNull() {
    service.save(null);
  }
  
  @Test
  public void testUpdate() {
    User user = service.get(100001);
    user.setEmail("newEmail@gmail.com");
    user.setEnabled(false);
    user.setPassword("password");
    
    service.update(user);
    
    assertThat(service.getAll(), is(Arrays.asList(ADMIN, user, USER2)));
  }

  @Test
  public void testDelete() {
    service.delete(100000);
    assertThat(service.getAll(), is(Arrays.asList(USER1, USER2)));
  }
  
  @Test(expected = NotFoundException.class)
  public void testNotFoundDelete() {
    service.delete(111111);
  }

  @Test
  public void testGet() {
    User adminFromDb = service.get(100000);
    assertEquals(adminFromDb, ADMIN);
  }
  
  @Test(expected = NotFoundException.class)
  public void testNotFoundGet() {
    service.get(1000000);
  }

  @Test
  public void testGetByEmail() {
    User user1FromDb = service.getByEmail("user1@gmail.com");
    assertEquals(user1FromDb, USER1);
  }

  @Test
  public void testGetAll() {
    assertThat(service.getAll(), is(Arrays.asList(ADMIN, USER1, USER2)));
  }

  @Test
  public void testGetIdsbyBudgetId() {
    assertThat(service.getbyBudgetId(100004), is(Arrays.asList(USER1, USER2)));
  }

}
