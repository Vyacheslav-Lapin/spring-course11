package lab.dao.jdbc;

import java.util.List;
import java.util.Optional;
import lab.dao.CountryDao;
import lab.model.Country;
import org.jetbrains.annotations.NotNull;

public interface CountryJdbcDao extends CountryDao, NamedParameterJdbcTemplateJdbcDao {

    String[][] COUNTRY_INIT_DATA = {
            {"Russian Federation", "RU"},
            {"Australia", "AU"},
            {"Canada", "CA"},
            {"France", "FR"},
            {"Hong Kong", "HK"},
            {"Iceland", "IC"},
            {"Japan", "JP"},
            {"Nepal", "NP"},
            {"Sweden", "SE"},
            {"Switzerland", "CH"},
            {"United Kingdom", "GB"},
            {"United States", "US"}};

    List<Country> getCountryListStartWith(@NotNull String name);

    void updateCountryName(@NotNull String codeName, @NotNull String newCountryName);

    void loadCountries();

    Optional<Country> getCountryByCodeName(@NotNull String codeName);
}
