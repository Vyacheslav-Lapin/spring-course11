package lab.dao.jdbc;

import static java.lang.String.format;

import java.util.List;
import java.util.Map;
import lab.commons.Private;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public interface JdbcDao {

    void update(String sql);

    <T> List<T> query(String sql, RowMapper<T> rowMapper);


    default void updateWithJdbcParams(@NotNull String sql, @NotNull Map<String, ?> params) {
        updateWithJdbcParams(sql, new MapSqlParameterSource(params));
    }

    default void updateWithJdbcParams(String sql, MapSqlParameterSource parameters) {
        updateWithJdbcParams(toSimpleSql(sql, parameters.getValues()));
    }

    void updateWithJdbcParams(String sql);

    void updateWithNamedParams(String sql, Map<String, ?> params);


    default <T> List<T> query(String sql, MapSqlParameterSource parameters, RowMapper<T> rowMapper) {
        return query(toSimpleSql(sql, parameters.getValues()), rowMapper);
    }


    @Private
    static String toSimpleSql(String sql, Map<String, ?> params) {
        for (Map.Entry<String, ?> param : params.entrySet()) {
            Object value = param.getValue();
            sql = sql.replaceFirst(":" + param,
                    (value instanceof CharSequence) ? format("'%s'", value) : value.toString());
        }
        return sql;
    }
}
