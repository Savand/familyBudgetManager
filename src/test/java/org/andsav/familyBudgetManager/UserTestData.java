package org.andsav.familyBudgetManager;

import org.andsav.familyBudgetManager.service.UserServiceTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static org.andsav.familyBudgetManager.model.abstractentity.BaseEntity.START_SEQ;

import org.andsav.familyBudgetManager.model.User;
import org.andsav.familyBudgetManager.model.enums.Role;

public class UserTestData {
  
  private static final Log LOG = LogFactory.getLog(UserServiceTest.class);
  
  public static final int ADMIN_ID = START_SEQ;
  public static final int USER_ID1 = START_SEQ + 1;
  public static final int USER_ID2 = START_SEQ + 2;

  public static final User ADMIN = new User(ADMIN_ID, "admin", null, "admin@gmail.com", "password", Role.ROLE_ADMIN, Role.ROLE_USER);
  public static final User USER1 = new User(USER_ID1, "User1", null, "user1@gmail.com", "password", Role.ROLE_USER);
  public static final User USER2 = new User(USER_ID2, "User2", null, "user2@gmail.com", "password", Role.ROLE_USER);
  
  
}
