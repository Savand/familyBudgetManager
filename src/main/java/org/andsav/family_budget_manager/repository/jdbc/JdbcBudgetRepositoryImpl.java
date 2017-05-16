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

import java.util.List;

import javax.sql.DataSource;

@Repository
public class JdbcBudgetRepositoryImpl implements BudgetRepository {

    private static final BeanPropertyRowMapper<Budget> ROW_MAPPER =
            BeanPropertyRowMapper.newInstance(Budget.class);
    private static final String SELECT_ALL_FROM_BUDGETS =
            "SELECT id, budget_name as name, description, budget_per_day, initial_budget_amount as budgetAmount FROM budgets ";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertBudget;

    @Autowired
    public JdbcBudgetRepositoryImpl(DataSource dataSource) {
        this.insertBudget = new SimpleJdbcInsert(dataSource).withTableName("budgets")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Budget save(Budget budget) {
        MapSqlParameterSource map = new MapSqlParameterSource().addValue("id", budget.getId())
                .addValue("creation_date", budget.getCreationDate())
                .addValue("budget_name", budget.getName())
                .addValue("description", budget.getDescription())
                .addValue("budget_creator_id", budget.getBudgetCreator().getId())
                .addValue("budget_per_day", budget.getBudgetPerDay());

        if (budget.isNew()) {
            Number id = insertBudget.executeAndReturnKey(map);
            budget.setId(id.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE budgets SET last_update= now(), budget_name= :budget_name, description= :description,"
                            + "budget_creator_id= :budget_creator_id, budget_per_day= :budget_per_day WHERE id=:id",
                    map);
        }

        return budget;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM budgets WHERE id=?", id) != 0;
    }

    @Override
    public List<Budget> getAll() {
        return jdbcTemplate.query(SELECT_ALL_FROM_BUDGETS + "ORDER BY id", ROW_MAPPER);
    }

    @Override
    public Budget get(int id) {
        List<Budget> budgets =
                jdbcTemplate.query(SELECT_ALL_FROM_BUDGETS + "WHERE id=?", ROW_MAPPER, id);
        Integer userId = jdbcTemplate.queryForObject(
                "SELECT budget_creator_id from budgets where id =" + id, Integer.class);
        Budget budget = DataAccessUtils.singleResult(budgets);
        budget.setBudgetCreator(userRepository.get(userId));
        return budget;
    }



}
