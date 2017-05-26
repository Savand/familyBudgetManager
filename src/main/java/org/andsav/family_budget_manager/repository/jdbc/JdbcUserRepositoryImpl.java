package org.andsav.family_budget_manager.repository.jdbc;

import org.andsav.family_budget_manager.model.User;
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
public class JdbcUserRepositoryImpl implements UserRepository {//TODO add handling for user roles. The sole reason for tests failing with jdbc profiling

    private static final BeanPropertyRowMapper<User> ROW_MAPPER =
            BeanPropertyRowMapper.newInstance(User.class);
    private static final String SELECT_FROM_USERS =
            "SELECT id, creation_date, last_update, user_name as name, email, password, user_icon, enabled FROM users ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource).withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("creation_date", user.getCreationDate())
                .addValue("user_name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("user_icon", user.getUserIcon())
                .addValue("enabled", user.isEnabled());

        if (user.isNew()) {
            Number id = insertUser.executeAndReturnKey(map);
            user.setId(id.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE users SET last_update= now(), user_name= :user_name, email= :email,"
                            + "password= :password, user_icon= :user_icon, enabled= :enabled WHERE id=:id",
                    map);
        }

        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query(SELECT_FROM_USERS + "WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users =
                jdbcTemplate.query(SELECT_FROM_USERS + "WHERE email=?", ROW_MAPPER, email);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(SELECT_FROM_USERS + "ORDER BY email, user_name", ROW_MAPPER);
    }

}
