package org.andsav.familyBudgetManager;

import org.andsav.familyBudgetManager.model.User;
import org.andsav.familyBudgetManager.model.enums.Role;

public interface PreparedMeansFlowTestData {

  public static final User ADMIN = new User(100000, "Admin", null, "admin@gmail.com", "$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju", Role.ADMIN, Role.USER);
  public static final User USER1 = new User(100001, "User1", null, "user1@gmail.com", "$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni", Role.USER);
  public static final User USER2 = new User(100002, "User2", null, "user2@gmail.com", "$2a$10$Sh0ZD2NFrzSDGBMGDBMijaoiebinlkdnbgADFKBAKDGbpmbgdpadk", Role.USER);

}
