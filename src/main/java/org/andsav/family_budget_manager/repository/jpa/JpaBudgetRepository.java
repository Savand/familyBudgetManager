package org.andsav.family_budget_manager.repository.jpa;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.repository.BudgetRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
public class JpaBudgetRepository implements BudgetRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Budget save(Budget budget) {
        if(budget.isNew()){
            em.persist(budget);
        } else {
            em.merge(budget);
        }
        
        return budget;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Budget.DELETE).setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Budget> getAll() {
        return em.createNamedQuery(Budget.ALL_SORTED, Budget.class).getResultList();
    }

    @Override
    public Budget get(int id) {
        return em.find(Budget.class, id);
    }

   


}
