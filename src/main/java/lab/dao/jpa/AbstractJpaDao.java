package lab.dao.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public abstract class AbstractJpaDao implements JpaDao {

	@SuppressWarnings("WeakerAccess")
	protected EntityManagerFactory emf;

	@PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
}