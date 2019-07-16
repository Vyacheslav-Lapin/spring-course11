package lab.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lab.model.Country;
import lab.model.simple.SimpleCountry;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

//@Component
@AllArgsConstructor
public class CountryJdbcDaoImpl implements CountryJdbcDao {

    private static final String ID = "id";
    private static final String CODE_NAME = "codeName";
    private static final String NAME = "name";
    private final NamedParameterJdbcDaoSupport namedParameterJdbcDaoSupport;

    @Override
    public NamedParameterJdbcDaoSupport get() {
        return namedParameterJdbcDaoSupport;
    }

    private static final String GET_ALL_COUNTRIES_SQL = "SELECT id, name, code_name FROM country";
    private static final String GET_COUNTRIES_BY_NAME_SQL = "SELECT id, name, code_name FROM country WHERE name LIKE :name";
    private static final String GET_COUNTRY_BY_NAME_SQL = "SELECT id, name, code_name FROM country WHERE name = :name";
    private static final String GET_COUNTRY_BY_CODE_NAME_SQL = "SELECT id, name, code_name FROM country WHERE code_name = :codeName";
    private static final String LOAD_COUNTRIES_SQL = "INSERT INTO country (name, code_name) VALUES (:name, :codeName)";
    private static final String UPDATE_COUNTRY_NAME_SQL = "UPDATE country SET name = :name WHERE code_name = :codeName";
    private static final String DELETE_COUNTRY_BY_ID_SQL = "DELETE FROM country WHERE id = :id";

    private static final RowMapper<Country> COUNTRY_ROW_MAPPER =
            (rs, rowNum) -> new SimpleCountry(
                    rs.getInt(ID),
                    rs.getString(NAME),
                    rs.getString(CODE_NAME));

    @Override
    public void save(@NotNull Country country) {
        updateCountryName(country.getName(), country.getCodeName());
    }

    @Override
    public void delete(@NotNull Country country) {
        updateWithJdbcParams(DELETE_COUNTRY_BY_ID_SQL, new MapSqlParameterSource(ID, country.getId()));
    }

    @Override
    public List<Country> getAllCountries() {
        return query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
    }

    @Nullable
    @Override
    public Optional<Country> getCountryByName(@NotNull String name) {

        List<Country> countryList = query(
                GET_COUNTRY_BY_NAME_SQL,
                new MapSqlParameterSource(NAME, name),
                COUNTRY_ROW_MAPPER);

        return Optional.ofNullable(countryList.get(0));

    }

    @Override
    public List<Country> getCountryListStartWith(@NotNull String name) {
        return query(
                GET_COUNTRIES_BY_NAME_SQL,
                new MapSqlParameterSource(NAME, name + "%"),
                COUNTRY_ROW_MAPPER);
    }

    @Override
    public void updateCountryName(@NotNull String codeName, @NotNull String newCountryName) {
        Map<String, String> params = new HashMap<>();
        params.put(NAME, newCountryName);
        params.put(CODE_NAME, codeName);
        updateWithJdbcParams(UPDATE_COUNTRY_NAME_SQL, params);
    }

    @Override
    public void loadCountries() {

        if (getAllCountries().isEmpty()) {
            @NotNull NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                    namedParameterJdbcDaoSupport.getNamedParameterJdbcTemplate();
            for (String[] countryData : COUNTRY_INIT_DATA) {
                HashMap<String, String> params = new HashMap<>();
                params.put(NAME, countryData[0]);
                params.put(CODE_NAME, countryData[1]);
                updateWithJdbcParams(LOAD_COUNTRIES_SQL, params);
            }
        }
    }

    @Override
    public Optional<Country> getCountryByCodeName(@NotNull String codeName) {
        List<Country> countries = query(GET_COUNTRY_BY_CODE_NAME_SQL,
                new MapSqlParameterSource(CODE_NAME, codeName),
                COUNTRY_ROW_MAPPER);

        return Optional.ofNullable(countries.get(0));
    }
}
