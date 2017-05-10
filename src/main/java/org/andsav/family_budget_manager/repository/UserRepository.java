package org.andsav.family_budget_manager.repository;

import org.andsav.family_budget_manager.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

}
