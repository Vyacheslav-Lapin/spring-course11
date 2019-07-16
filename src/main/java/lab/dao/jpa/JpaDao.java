package lab.dao.jpa;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@FunctionalInterface
public interface JpaDao extends Supplier<EntityManagerFactory> {

    default <T> T mapEntityManagerInTransaction(Function<EntityManager, T> entityManagerMapper) {
        return mapEntityManager(entityManager -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            T result = entityManagerMapper.apply(entityManager);
            transaction.commit();
            return result;
        });
    }

    default void withEntityManagerInTransaction(Consumer<EntityManager> entityManagerConsumer) {
        withEntityManager(entityManager -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManagerConsumer.accept(entityManager);
            transaction.commit();
        });
    }

    default <T> T mapEntityManager(Function<EntityManager, T> entityManagerMapper) {
        EntityManager em = null;
        try {
            return entityManagerMapper.apply(
                    em = get().createEntityManager());
        } finally {
            if (em != null)
                em.close();
        }
    }

    default void withEntityManager(Consumer<EntityManager> entityManagerConsumer) {
        EntityManager em = get().createEntityManager();
        entityManagerConsumer.accept(em);
        em.close();
    }
}
