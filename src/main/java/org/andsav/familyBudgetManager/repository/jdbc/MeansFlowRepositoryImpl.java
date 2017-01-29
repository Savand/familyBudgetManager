package org.andsav.familyBudgetManager.repository.jdbc;

import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.andsav.familyBudgetManager.model.Budget;
import org.andsav.familyBudgetManager.model.MeansFlow;
import org.andsav.familyBudgetManager.repository.MeansFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MeansFlowRepositoryImpl implements MeansFlowRepository {

  private static final BeanPropertyRowMapper<MeansFlow> ROW_MAPPER = BeanPropertyRowMapper.newInstance(MeansFlow.class);
  private static final String SELECT_ALL_FROM_MEANSFLOW = "SELECT id, description, operation_data_time, amount, user_id, meansflow_type_id FROM meansflow ";
  
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
    MapSqlParameterSource map = new MapSqlParameterSource()
        .addValue("id", meansFlow.getId())
        .addValue("description", meansFlow.getDescription())
        .addValue("user_id", meansFlow.getByUser().getId())
        .addValue("operation_date_time", meansFlow.getOperationDate())
        .addValue("", meansFlow.getGoodsType().ordinal()
            );
    
    if(meansFlow.isNew()){
      Number id = insertMeansFlow.executeAndReturnKey(map);
      meansFlow.setId(id.intValue());
    } else {
      namedParameterJdbcTemplate.update(
          "UPDATE budgeansflow SET last_update= now(), description= :description,"
          + "user_id= :user_id, operation_date_time= :operation_date_time WHERE id=:id", map);
    }
    return meansFlow;
  }

  @Override
  public boolean delete(Integer meansFlowId) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public MeansFlow get(Integer meansFlowId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<MeansFlow> getByBudgetId(Integer budgetId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<MeansFlow> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate, LocalDateTime endDate) {
    // TODO Auto-generated method stub
    return null;
  }

}
