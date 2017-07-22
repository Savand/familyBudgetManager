package org.andsav.family_budget_manager.repository.jdbc;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.model.enums.FundsFlowType;
import org.andsav.family_budget_manager.repository.FundsFlowRepository;
import org.andsav.family_budget_manager.util.FundsFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.sql.DataSource;

@Repository
public class JdbcFundsFlowRepositoryImpl implements FundsFlowRepository {

  private static final RowMapper<FundsFlow> ROW_MAPPER = new RowMapper<FundsFlow>() {

    @Override
    public FundsFlow mapRow(ResultSet rs, int rowNum) throws SQLException {
      FundsFlow ff = new FundsFlow();
      ff.setId(rs.getInt("id"));
      ff.setCreationDate(convertToLocalDateTime(rs.getDate("creation_date")));
      ff.setLastUpdate(convertToLocalDateTime(rs.getDate("last_update")));
      ff.setDescription(rs.getString("description"));
      ff.setOperationDateTime(convertToLocalDateTime(rs.getDate("operation_date_time")));
      ff.setAmount(rs.getInt("amount"));
      ff.setFundsFlowType(FundsFlowType.valueOf(rs.getString("funds_flow_type")));
      return ff;
    }

    private LocalDateTime convertToLocalDateTime(Date date) {
      if (date == null)
        return null;
      Instant instant = Instant.ofEpochMilli(date.getTime());
      return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

  };

  private static final String SELECT_FROM_FUNDSFLOW = "SELECT * FROM fundsflows ";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private SimpleJdbcInsert insertFundsFlow;

  @Autowired
  public JdbcFundsFlowRepositoryImpl(DataSource dataSource) {
    this.insertFundsFlow = new SimpleJdbcInsert(dataSource).withTableName("fundsflows").usingGeneratedKeyColumns("id");
  }

  @Override
  public FundsFlow save(FundsFlow fundsFlow, int budgetId) {
    FundsFlowUtil.signAmountRegardingType(fundsFlow);
    MapSqlParameterSource map = new MapSqlParameterSource().addValue("id", fundsFlow.getId())
        .addValue("creation_date", fundsFlow.getCreationDate()).addValue("description", fundsFlow.getDescription())
        .addValue("funds_flow_type", fundsFlow.getFundsFlowType().toString())
        .addValue("operation_date_time", fundsFlow.getOperationDateTime())
        // .addValue("user_id", fundsFlow.getByUser().getId())
        .addValue("amount", fundsFlow.getAmount());
    // .addValue("budget_id", fundsFlow.getBudget().getId());

    if (fundsFlow.isNew()) {
      Number id = insertFundsFlow.executeAndReturnKey(map);
      fundsFlow.setId(id.intValue());
    } else {
      namedParameterJdbcTemplate
          .update("UPDATE fundsflows SET last_update= now(), description= :description, amount= :amount, "
              + "funds_flow_type= :funds_flow_type, operation_date_time= :operation_date_time WHERE id=:id", map);
    }
    return fundsFlow;
  }

  @Override
  public boolean delete(int id, int budgetId) {
    return jdbcTemplate.update("DELETE FROM fundsflows WHERE id=? AND budget_id=?", id, budgetId) != 0;
  }

  @Override
  public FundsFlow get(int id, int budgetId) {
    List<FundsFlow> fundsFlowPositions = jdbcTemplate.query(SELECT_FROM_FUNDSFLOW + "WHERE id=? AND budget_id=?",
        ROW_MAPPER, id, budgetId);
    FundsFlow fundsFlow = DataAccessUtils.singleResult(fundsFlowPositions);

    return fundsFlow;
  }

  @Override
  public List<FundsFlow> getAll(int budgetId) {
    return jdbcTemplate.query(SELECT_FROM_FUNDSFLOW + "WHERE budget_id=? ORDER BY id", ROW_MAPPER, budgetId);
  }

  @Override
  public List<FundsFlow> getAllBetweenDates(int budgetId, LocalDateTime startDate, LocalDateTime endDate) {
    return jdbcTemplate.query(
        SELECT_FROM_FUNDSFLOW + "WHERE budget_id=? and operation_date_time between ? and ? ORDER BY id", ROW_MAPPER,
        budgetId, startDate, endDate);
  }

}
