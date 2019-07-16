package lab.dao;

import java.util.List;
import java.util.Optional;
import lab.model.Country;
import org.jetbrains.annotations.NotNull;

public interface CountryDao {

	void save(@NotNull Country country);

	void delete(@NotNull Country country);

	List<Country> getAllCountries();

    Optional<Country> getCountryByName(@NotNull String name);

}
