package org.andsav.family_budget_manager.repository.jdbc;

import org.andsav.family_budget_manager.model.Meansflow;
import org.andsav.family_budget_manager.model.enums.MeansflowType;
import org.andsav.family_budget_manager.repository.BudgetRepository;
import org.andsav.family_budget_manager.repository.MeansflowRepository;
import org.andsav.family_budget_manager.repository.UserRepository;
import org.andsav.family_budget_manager.util.MeansFlowUtil;
import org.andsav.family_budget_manager.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

@Repository
public class MeansFlowRepositoryImpl implements MeansflowRepository {

    private static final BeanPropertyRowMapper<Meansflow> ROW_MAPPER =
            BeanPropertyRowMapper.newInstance(Meansflow.class);
    private static final String SELECT_ALL_FROM_MEANSFLOW =
            "SELECT id, description, operation_date_time, amount, user_id, budget_id, goods_type FROM meansflow ";

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
        this.insertMeansFlow = new SimpleJdbcInsert(dataSource).withTableName("meansflow")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Meansflow save(Meansflow meansFlow) {
        MeansFlowUtil.signAmountRegardingType(meansFlow);
        MapSqlParameterSource map = new MapSqlParameterSource().addValue("id", meansFlow.getId())
                .addValue("creation_date", meansFlow.getCreationDate())
                .addValue("description", meansFlow.getDescription())
                .addValue("goods_type", meansFlow.getType().ordinal() + 1)
                .addValue("operation_date_time", meansFlow.getOperationDateTime())
                .addValue("user_id", meansFlow.getByUser().getId())
                .addValue("amount", meansFlow.getAmount())
                .addValue("budget_id", meansFlow.getBudget().getId());

        if (meansFlow.isNew()) {
            Number id = insertMeansFlow.executeAndReturnKey(map);
            meansFlow.setId(id.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE meansflow SET last_update= now(), description= :description, amount= :amount, user_id= :user_id, budget_id= :budget_id, "
                            + "goods_type= :goods_type, operation_date_time= :operation_date_time WHERE id=:id",
                    map);
        }
        return meansFlow;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM meansflow WHERE id=?", id) != 0;
    }

    @Override
    public Meansflow get(Integer id) {
        List<Meansflow> meansFlowPositions =
                jdbcTemplate.query(SELECT_ALL_FROM_MEANSFLOW + "WHERE id=?", ROW_MAPPER, id);
        Meansflow meansFlow = DataAccessUtils.singleResult(meansFlowPositions);

        try {
            Integer userId = jdbcTemplate
                    .queryForObject("SELECT user_id from meansflow where id =" + id, Integer.class);
            meansFlow.setByUser(userRepository.get(userId));
            Integer meansFlowTypeId = jdbcTemplate.queryForObject(
                    "SELECT meansflow_type_id from meansflow where id =" + id, Integer.class);
            meansFlow.setGoodsType(MeansflowType.values()[meansFlowTypeId - 1]);
            Integer budgetId = jdbcTemplate.queryForObject(
                    "SELECT budget_id from meansflow where id =" + id, Integer.class);
            meansFlow.setBudget(budgetRepository.get(budgetId));
        } catch (DataAccessException e) {
            throw new NotFoundException(e.getMessage());
        }

        return meansFlow;
    }

    @Override
    public List<Meansflow> getByBudgetId(Integer budgetId) {
        return jdbcTemplate.query(SELECT_ALL_FROM_MEANSFLOW + "WHERE budget_id=? ORDER BY id",
                ROW_MAPPER, budgetId);
    }

    @Override
    public List<Meansflow> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate) {
        return jdbcTemplate.query(
                SELECT_ALL_FROM_MEANSFLOW
                        + "WHERE budget_id=? and operation_date_time between ? and ? ORDER BY id",
                ROW_MAPPER, budgetId, startDate, endDate);
    }

}
