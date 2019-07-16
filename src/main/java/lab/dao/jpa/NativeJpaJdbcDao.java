package lab.dao.jpa;

import java.util.List;
import lab.dao.jdbc.JdbcDao;
import org.springframework.jdbc.core.RowMapper;

@FunctionalInterface
public interface NativeJpaJdbcDao extends JdbcDao, JpaDao {

//        Query q = em.createNativeQuery("SELECT a.firstname, a.lastname FROM Author a");
//        List<Object[]> authors = q.getResultList();
//
//        for (Object[] a : authors) {
//            System.out.println("Author "
//                    + a[0]
//                    + " "
//                    + a[1]);
//        }

    @Override
    default void updateWithJdbcParams(String sql) {
        withEntityManagerInTransaction(entityManager ->
                entityManager.createNativeQuery(sql).executeUpdate());
    }

    @Override
    default <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        return
    }
}
