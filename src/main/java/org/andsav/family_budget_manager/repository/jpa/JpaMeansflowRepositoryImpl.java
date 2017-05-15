package org.andsav.family_budget_manager.repository.jpa;

import org.andsav.family_budget_manager.model.Meansflow;
import org.andsav.family_budget_manager.repository.MeansflowRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * 
 * @author Andrii_Savka
 *
 */
@Repository
@Transactional(readOnly = true)
public class JpaMeansflowRepositoryImpl implements MeansflowRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meansflow save(Meansflow meansFlow) {
        if (meansFlow.isNew()) {
            em.persist(meansFlow);
        } else {
            em.merge(meansFlow);
        }
        return meansFlow;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return em.createNamedQuery(Meansflow.BY_ID_DELETE).setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Meansflow get(Integer id) {
        return em.find(Meansflow.class, id);
    }

    @Override
    public List<Meansflow> getByBudgetId(Integer id) {
        return em.createNamedQuery(Meansflow.BY_BUDGET_ID, Meansflow.class).setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Meansflow> getByBudgetIdBetweenDates(Integer id, LocalDateTime startDate,
            LocalDateTime endDate) {
        return em.createNamedQuery(Meansflow.BY_BUDGET_ID_BETWEEN_DATES, Meansflow.class)
                .setParameter("id", id).setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
    }



}
