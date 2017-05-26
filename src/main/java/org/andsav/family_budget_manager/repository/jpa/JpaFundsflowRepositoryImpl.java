package org.andsav.family_budget_manager.repository.jpa;

import org.andsav.family_budget_manager.model.FundsFlow;
import org.andsav.family_budget_manager.repository.FundsFlowRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;


/**
 * 
 * @author Andrii_Savka
 *
 */
@Repository
@Transactional(readOnly = true)
public class JpaFundsflowRepositoryImpl implements FundsFlowRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public FundsFlow save(FundsFlow meansFlow, int id) {
        if (meansFlow.isNew()) {
            em.persist(meansFlow);
        } else {
            em.merge(meansFlow);
        }
        return meansFlow;
    }

    @Override
    @Transactional
    public boolean delete(int id, int budgetId) {
        return em.createNamedQuery(FundsFlow.DELETE)
                .setParameter("id", id)
                .setParameter("budgetId", budgetId)
                .executeUpdate() != 0;
    }

    @Override
    public FundsFlow get(int id, int budgetId) {
        return em.find(FundsFlow.class, id);
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<FundsFlow> cq = cb.createQuery(FundsFlow.class);
//        Root<FundsFlow> fundsFlow = cq.from(FundsFlow.class);
//        EntityType<FundsFlow> FundsFlow_ = fundsFlow.getModel();
//        Predicate idEqual = cb.equal(fundsFlow.get(FundsFlow_.id), id);
//        Predicate budgetIdEqual = cb.equal(fundsFlow.get(FundsFlow_.budget.id), id);
//        Predicate and = cb.and(idEqual, budgetIdEqual);
//        cq.where(and);
//        TypedQuery<FundsFlow> q = em.createQuery(cq);
//        FundsFlow ff = q.getSingleResult();
        
//        FundsFlow fundsFlow2 = resultList.stream()
//        .filter(result -> (id == result.getId()))
//        .filter(result -> (budgetId == result.getBudget().getId()))
//        .findFirst().orElse(null);
        
//        return ff;
        
    }

    @Override
    public List<FundsFlow> getAll(int id) {
        return em.createNamedQuery(FundsFlow.BY_BUDGET_ID, FundsFlow.class).setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<FundsFlow> getAllBetweenDates(int id, LocalDateTime startDate,
            LocalDateTime endDate) {
        return em.createNamedQuery(FundsFlow.BY_BUDGET_ID_BETWEEN_DATES, FundsFlow.class)
                .setParameter("id", id).setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
    }



}
