package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaUtil {

  @PersistenceContext
  private EntityManager em;

  public void clea2ndLevelHibernateCache() {
    Session s = (Session) em.getDelegate();
    SessionFactory sf = s.getSessionFactory();

    sf.getCache().evictQueryRegions();
    sf.getCache().evictDefaultQueryRegion();
    sf.getCache().evictCollectionRegions();
    sf.getCache().evictEntityRegions();

  }
}
