package org.andsav.family_budget_manager.repository.spring_data_jpa;

import org.andsav.family_budget_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    
    @Transactional
    @Modifying
    @Query(name = User.DELETE)
    int delete(@Param("id") int id);
    
    User getByEmail(String email);
    
}
