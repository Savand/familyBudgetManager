package org.andsav.family_budget_manager.repository.jpa;

import org.andsav.family_budget_manager.model.Budget;
import org.andsav.family_budget_manager.repository.BudgetRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * 
 * @author Andrii_Savka
 *
 */
@Repository
public class JpaBudgetRepository implements BudgetRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Budget save(Budget user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Integer> getIdsByUserId(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Budget> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Budget get(int id) {
        // TODO Auto-generated method stub
        return null;
    }

   


}
