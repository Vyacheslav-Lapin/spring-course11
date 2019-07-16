package lab.dao.jdbc;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

//@FunctionalInterface
public interface NamedParameterJdbcTemplateJdbcDao extends Supplier<NamedParameterJdbcDaoSupport>, JdbcDao {

    @Override
    default void updateWithJdbcParams(String sql, MapSqlParameterSource parameters) {
        //noinspection ConstantConditions
        @NotNull JdbcTemplate jdbcTemplate = get().getJdbcTemplate();
        jdbcTemplate.update(sql, parameters);
    }

    @Override
    default <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        //noinspection ConstantConditions
        @NotNull JdbcTemplate jdbcTemplate = get().getJdbcTemplate();
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    default <T> List<T> query(String sql, MapSqlParameterSource parameters, RowMapper<T> rowMapper) {
        //noinspection ConstantConditions
        @NotNull NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                get().getNamedParameterJdbcTemplate();

        return namedParameterJdbcTemplate.query(sql, parameters, rowMapper);
    }


    default void updateWithNamedParams(String sql, Map<String, ?> params) {
        //noinspection ConstantConditions
        @NotNull NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                get().getNamedParameterJdbcTemplate();

        namedParameterJdbcTemplate.update(sql, params);
    }
}
