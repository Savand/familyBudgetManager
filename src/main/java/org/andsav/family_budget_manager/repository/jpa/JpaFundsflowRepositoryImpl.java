package org.andsav.family_budget_manager.repository.jpa;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.repository.FundsflowRepository;
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
public class JpaFundsflowRepositoryImpl implements FundsflowRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public FundsFlow save(FundsFlow meansFlow) {
        if (meansFlow.isNew()) {
            em.persist(meansFlow);
        } else {
            em.merge(meansFlow);
        }
        return meansFlow;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(FundsFlow.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public FundsFlow get(int id) {
        return em.find(FundsFlow.class, id);
    }

    @Override
    public List<FundsFlow> getByBudgetId(Integer id) {
        return em.createNamedQuery(FundsFlow.BY_BUDGET_ID, FundsFlow.class).setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<FundsFlow> getByBudgetIdBetweenDates(Integer id, LocalDateTime startDate,
            LocalDateTime endDate) {
        return em.createNamedQuery(FundsFlow.BY_BUDGET_ID_BETWEEN_DATES, FundsFlow.class)
                .setParameter("id", id).setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
    }



}
