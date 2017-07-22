package org.andsav.family_budget_manager.repository.jdbc;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.repository.BudgetRepository;
import org.andsav.family_budget_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

@Repository
public class JdbcBudgetRepositoryImpl implements BudgetRepository {

  private static final BeanPropertyRowMapper<Budget> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Budget.class);
  private static final String SELECT_ALL_FROM_BUDGETS = "SELECT id, budget_name as name, description, budget_per_day FROM budgets ";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private SimpleJdbcInsert insertBudget;

  @Autowired
  public JdbcBudgetRepositoryImpl(DataSource dataSource) {
    this.insertBudget = new SimpleJdbcInsert(dataSource).withTableName("budgets").usingGeneratedKeyColumns("id");
  }

  @Override
  @Transactional
  public Budget save(Budget budget, int creatorId) {
    MapSqlParameterSource map = new MapSqlParameterSource().addValue("id", budget.getId())
        .addValue("creation_date", budget.getCreationDate()).addValue("last_update", budget.getLastUpdate())
        .addValue("budget_name", budget.getName())
        .addValue("budget_creator_id",
            budget.getBudgetCreator().getId() == null ? creatorId : budget.getBudgetCreator().getId())
        .addValue("budget_per_day", budget.getBudgetPerDay()).addValue("description", budget.getDescription());

    if (budget.isNew()) {
      Number id = insertBudget.executeAndReturnKey(map);
      jdbcTemplate.update("INSERT INTO budgets_users values(?, ?)", id, creatorId);
      budget.setId(id.intValue());
    } else {
      namedParameterJdbcTemplate
          .update("UPDATE budgets SET last_update= now(), budget_name= :budget_name, description= :description,"
              + "budget_creator_id= :budget_creator_id, budget_per_day= :budget_per_day WHERE id=:id", map);
    }

    return budget;
  }

  @Override
  @Transactional
  public boolean delete(int id, int creatorId) {
    return jdbcTemplate.update("DELETE FROM budgets WHERE id=? AND budget_creator_id=?", id, creatorId) != 0;
  }

  @Override
  public Set<Budget> getAll(int contributorId) {
    Set<Budget> budgets = new HashSet<>();
    budgets.addAll(jdbcTemplate.query(
        SELECT_ALL_FROM_BUDGETS + " WHERE id in (select budget_id from budgets_users where user_id=?) ORDER BY id",
        ROW_MAPPER, contributorId));
    return budgets;
  }

  @Override
  public Budget get(int id) {
    List<Budget> budgets = jdbcTemplate.query(SELECT_ALL_FROM_BUDGETS + "WHERE id=?", ROW_MAPPER, id);
    Integer userId = jdbcTemplate.queryForObject("SELECT budget_creator_id from budgets where id =" + id,
        Integer.class);
    Budget budget = DataAccessUtils.singleResult(budgets);
    budget.setBudgetCreator(userRepository.get(userId));
    return budget;
  }

}
