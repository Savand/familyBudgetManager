package org.andsav.family_budget_manager;

import org.andsav.family_budget_manager.model.User;
import org.andsav.family_budget_manager.model.enums.Role;

import java.util.Objects;

public interface PreparedUserTestData {

    public static final User ADMIN = new User(100000, "Admin", null, "admin@gmail.com",
            "$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju", Role.ROLE_ADMIN, Role.ROLE_USER);
    public static final User USER1 = new User(100001, "User1", null, "user1@gmail.com",
            "$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni", Role.ROLE_USER);
    public static final User USER2 = new User(100002, "User2", null, "user2@gmail.com",
            "$2a$10$Sh0ZD2NFrzSDGBMGDBMijaoiebinlkdnbgADFKBAKDGbpmbgdpadk", Role.ROLE_USER);
    
    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                            (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.isEnabled(), actual.isEnabled())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );



}
