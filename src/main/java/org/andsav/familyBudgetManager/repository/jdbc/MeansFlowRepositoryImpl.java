package org.andsav.familyBudgetManager.repository.jdbc;

import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.andsav.familyBudgetManager.model.MeansFlow;
import org.andsav.familyBudgetManager.model.enums.MeansflowType;
import org.andsav.familyBudgetManager.repository.BudgetRepository;
import org.andsav.familyBudgetManager.repository.MeansFlowRepository;
import org.andsav.familyBudgetManager.repository.UserRepository;
import org.andsav.familyBudgetManager.util.MeansFlowUtil;
import org.andsav.familyBudgetManager.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MeansFlowRepositoryImpl implements MeansFlowRepository {

  private static final BeanPropertyRowMapper<MeansFlow> ROW_MAPPER = BeanPropertyRowMapper.newInstance(MeansFlow.class);
  private static final String SELECT_ALL_FROM_MEANSFLOW = "SELECT id, description, operation_date_time, amount, user_id, budget_id, meansflow_type_id FROM meansflow ";
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private BudgetRepository budgetRepository;
  
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  private SimpleJdbcInsert insertMeansFlow;
  
  @Autowired
  public MeansFlowRepositoryImpl(DataSource dataSource) {
      this.insertMeansFlow = new SimpleJdbcInsert(dataSource)
              .withTableName("meansflow")
              .usingGeneratedKeyColumns("id");
  }
  
  @Override
  public MeansFlow save(MeansFlow meansFlow) {
    MeansFlowUtil.signAmountRegardingType(meansFlow);
    MapSqlParameterSource map = new MapSqlParameterSource()
        .addValue("id", meansFlow.getId())
        .addValue("creation_date", meansFlow.getCreationDate())
        .addValue("description", meansFlow.getDescription())
        .addValue("meansflow_type_id", meansFlow.getType().ordinal() + 1)
        .addValue("operation_date_time", meansFlow.getOperationDateTime())
        .addValue("user_id", meansFlow.getByUser().getId())
        .addValue("amount", meansFlow.getAmount())
        .addValue("budget_id", meansFlow.getBudget().getId()
            );
    
    if(meansFlow.isNew()){
      Number id = insertMeansFlow.executeAndReturnKey(map);
      meansFlow.setId(id.intValue());
    } else {
      namedParameterJdbcTemplate.update(
          "UPDATE meansflow SET last_update= now(), description= :description, amount= :amount, user_id= :user_id, budget_id= :budget_id, "
          + "meansflow_type_id= :meansflow_type_id, operation_date_time= :operation_date_time WHERE id=:id", map);
    }
    return meansFlow;
  }

  @Override
  public boolean delete(Integer id) {
    return jdbcTemplate.update("DELETE FROM meansflow WHERE id=?", id) != 0;
  }

  @Override
  public MeansFlow get(Integer id) {
    List<MeansFlow> meansFlowPositions = jdbcTemplate.query(SELECT_ALL_FROM_MEANSFLOW + "WHERE id=?", ROW_MAPPER, id);
    MeansFlow meansFlow = DataAccessUtils.singleResult(meansFlowPositions);
    
    try {
      Integer userId = jdbcTemplate.queryForObject("SELECT user_id from meansflow where id =" + id, Integer.class);
      meansFlow.setByUser(userRepository.get(userId));
      Integer meansFlowTypeId = jdbcTemplate.queryForObject("SELECT meansflow_type_id from meansflow where id =" + id, Integer.class);
      meansFlow.setGoodsType(MeansflowType.values()[meansFlowTypeId - 1]);
      Integer budgetId = jdbcTemplate.queryForObject("SELECT budget_id from meansflow where id =" + id, Integer.class);
      meansFlow.setBudget(budgetRepository.get(budgetId));
    } catch (DataAccessException e) {
      throw new NotFoundException(e.getMessage());
    }
    
    return meansFlow;
  }

  @Override
  public List<MeansFlow> getByBudgetId(Integer budgetId) {
    return jdbcTemplate.query(SELECT_ALL_FROM_MEANSFLOW + "WHERE budget_id=? ORDER BY id", ROW_MAPPER, budgetId);
  }

  @Override
  public List<MeansFlow> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate, LocalDateTime endDate) {
    return jdbcTemplate.query(SELECT_ALL_FROM_MEANSFLOW + "WHERE budget_id=? and operation_date_time between ? and ? ORDER BY id", ROW_MAPPER, budgetId, startDate, endDate);
  }

}
