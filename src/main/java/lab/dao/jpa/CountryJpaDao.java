package lab.dao.jpa;

import java.util.List;
import java.util.Optional;
import lab.dao.CountryDao;
import lab.model.Country;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public class CountryJpaDao extends AbstractJpaDao implements CountryDao {

    @Override
    public void save(@NotNull Country country) {
        withEntityManagerInTransaction(entityManager ->
                entityManager.persist(country));
    }

    @Override
    public void delete(@NotNull Country country) {
        withEntityManagerInTransaction(em ->
                em.remove(em.contains(country) ? country : em.merge(country)));
    }

    @Override
    public List<Country> getAllCountries() {
        return mapEntityManager(entityManager ->
                entityManager.createQuery(
                        "SELECT c FROM SimpleCountry c",
                        Country.class)
                        .getResultList());
    }

    @Nullable
    @Override
    public Optional<Country> getCountryByName(@NotNull String name) {
        return mapEntityManager(entityManager -> Optional.ofNullable(
                entityManager.createQuery(
                        "SELECT c FROM SimpleCountry c WHERE c.name LIKE :name",
                        Country.class)
                        .setParameter("name", name)
                        .getSingleResult())
        );
    }

}
