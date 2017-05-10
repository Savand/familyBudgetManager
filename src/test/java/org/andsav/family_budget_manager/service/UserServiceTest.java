package org.andsav.family_budget_manager.service;


import static org.andsav.family_budget_manager.PreparedUserTestData.ADMIN;
import static org.andsav.family_budget_manager.PreparedUserTestData.MATCHER;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER1;
import static org.andsav.family_budget_manager.PreparedUserTestData.USER2;

import org.andsav.family_budget_manager.model.User;
import org.andsav.family_budget_manager.model.enums.Role;
import org.andsav.family_budget_manager.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;


@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql("classpath:db/populateDB.sql")
public class UserServiceTest {

    @Autowired
    UserService service;

    @Test
    public void testSave() {
        User newTestUser = new User("new User", null, "newUser@gmail.com", "password", Role.ROLE_USER);
        User savedTestUser = service.save(newTestUser);
        newTestUser.setId(savedTestUser.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, newTestUser, USER1, USER2), service.getAll());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNull() {
        service.save(null);
    }
    
    @Test(expected = DataAccessException.class)
    public void testSaveDuplicateEmail() {
        User newTestUserDuplicateEmail = new User("new User", null, "user2@gmail.com", "password", Role.ROLE_USER);
        service.save(newTestUserDuplicateEmail);
    }

    @Test
    public void testUpdate() {
        User user = service.get(100001);
        user.setEmail("newEmail@gmail.com");
        user.setEnabled(false);
        user.setPassword("password");
        service.update(user);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, user, USER2), service.getAll());
    }

    @Test
    public void testDelete() {
        service.delete(100000);
        MATCHER.assertCollectionEquals(Arrays.asList(USER1, USER2), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() {
        service.delete(111111);
    }

    @Test
    public void testGetUserById() {
        User adminFromDb = service.get(100000);
        MATCHER.assertEquals(ADMIN, adminFromDb);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundGet() {
        service.get(1000000);
    }

    @Test
    public void testGetByEmail() {
        User user1FromDb = service.getByEmail("user1@gmail.com");
        MATCHER.assertEquals(USER1, user1FromDb);
    }

    @Test
    public void testGetAll() {
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER1, USER2), service.getAll());
    }


}
