package org.andsav.family_budget_manager.repository.jpa;

import org.andsav.family_budget_manager.model.Meansflow;
import org.andsav.family_budget_manager.repository.MeansflowRepository;
import org.springframework.stereotype.Repository;

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
public class JpaMeansflowRepository implements MeansflowRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meansflow save(Meansflow meansFlow) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Integer meansFlowId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Meansflow get(Integer meansFlowId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Meansflow> getByBudgetId(Integer budgetId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Meansflow> getByBudgetIdBetweenDates(Integer budgetId, LocalDateTime startDate,
            LocalDateTime endDate) {
        // TODO Auto-generated method stub
        return null;
    }

   

   


}
