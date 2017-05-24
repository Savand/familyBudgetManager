package org.andsav.family_budget_manager.repository.jpa;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.repository.BudgetRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/**
 * 
 * @author Andrii_Savka
 *
 */
@Repository
@Transactional(readOnly = true)
public class JpaBudgetRepositoryImpl implements BudgetRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Budget save(Budget budget, int creatorId) {
        if(budget.isNew()){
            em.persist(budget);
        } else {
            em.merge(budget);
        }
        
        return budget;
    }

    @Override
    @Transactional
    public boolean delete(int id, int creatorId) {
        return em.createNamedQuery(Budget.DELETE).setParameter("id", id).setParameter("userId", creatorId)
                .executeUpdate() != 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Budget> getAll(int contributorId) {
        Query nativeQuery = em.createNativeQuery("SELECT * FROM budgets b WHERE b.id in (SELECT bu.budget_id FROM budgets_users bu WHERE bu.user_id=?) ORDER BY b.id", Budget.class);
        nativeQuery.setParameter(1, contributorId);
        Set<Budget> budgets = new HashSet<>();
        budgets.addAll(nativeQuery.getResultList());
        return budgets;
    }

    @Override
    public Budget get(int id) {
        return em.find(Budget.class, id);
    }

   


}
