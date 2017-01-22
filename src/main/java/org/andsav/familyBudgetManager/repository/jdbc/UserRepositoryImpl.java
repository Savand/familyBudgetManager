package org.andsav.familyBudgetManager.repository.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.andsav.familyBudgetManager.model.User;
import org.andsav.familyBudgetManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryImpl implements UserRepository {

  private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);
  private static final String SELECT_ALL_FROM_USERS = "SELECT id, user_name as name, email, password, user_icon as userIcon, enabled FROM users ";
  
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  private SimpleJdbcInsert insertUser;

  @Autowired
  public UserRepositoryImpl(DataSource dataSource) {
      this.insertUser = new SimpleJdbcInsert(dataSource)
              .withTableName("USERS")
              .usingGeneratedKeyColumns("id");
  }
  
  @Override
  public User save(User user) {
    MapSqlParameterSource map = new MapSqlParameterSource()
        .addValue("id", user.getId())
        .addValue("creation_data_time", user.getCreationDate())
        .addValue("last_update_time", user.getLastUpdateDate())
        .addValue("user_name", user.getName())
        .addValue("email", user.getEmail())
        .addValue("password", user.getPassword())
        .addValue("user_icon", user.getUserIcon())
        .addValue("enabled", user.isEnabled());
    
    if(user.isNew()){
      Number id = insertUser.executeAndReturnKey(map);
      user.setId(id.intValue());
    } else {
      namedParameterJdbcTemplate.update(
          "UPDATE users SET last_update_time= :lastUpdateDate, user_name= :name, email= :email,"
          + "password= :password, user_icon= :userIcon, enabled= :enabled", map);
    }

    return user;
  }

  @Override
  public boolean delete(int id) {
    return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
  }

  @Override
  public User get(int id) {
    User queryForObject = jdbcTemplate.queryForObject(SELECT_ALL_FROM_USERS + "WHERE id=?", ROW_MAPPER, id);
    return queryForObject;
  }

  @Override
  public User getByEmail(String email) {
    return jdbcTemplate.queryForObject(SELECT_ALL_FROM_USERS + "WHERE email=?", ROW_MAPPER, email);
  }

  @Override
  public List<User> getAll() {
    return jdbcTemplate.query(SELECT_ALL_FROM_USERS + "ORDER BY email, user_name", ROW_MAPPER);
  }

  @Override
  public List<Integer> getIdsByBudgetId(Integer budgetId) {
    return jdbcTemplate.queryForList("SELECT user_id FROM users_budgets WHERE budget_id=?", Integer.class, budgetId);
  }

}
