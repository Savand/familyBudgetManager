package org.andsav.familyBudgetManager.model.abstractentity;

import java.time.LocalDateTime;

/**
 * The {@code BaseEntity} abstract class is intended to be derived by
 * classes which objects state is to be persisted. Comprises following fields:
 * 
 * <blockquote><pre>
 * {@code Long} id,
 * {@code Date} creationDateTime,
 * {@code Date} lastUpdateDateTime,
 * <pre></blockquote><p> 
 * 
 * 
 * @See {@link org.andsav.familyBudgetManager.model.abstractentity.NamedEntity}
 * 
 * @author asavka
 *
 */
public abstract class BaseEntity {
  
  private Integer id;
  private LocalDateTime initializationDate;
  private LocalDateTime lastUpdateDate;
  
  public BaseEntity() {
    
    if(isNew()){
      initializationDate = LocalDateTime.now();
    } else {
      this.lastUpdateDate = LocalDateTime.now();
    }
    
  }

  public BaseEntity(Integer id) {

    if(isNew()){
      initializationDate = LocalDateTime.now();
    } else {
      this.lastUpdateDate = LocalDateTime.now();
    }
    
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public LocalDateTime getInitializationDate() {
    return initializationDate;
  }
  
  public LocalDateTime getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }
  
  protected boolean isNew(){
    return id == null;
  }

  @Override
  public String toString() {
    return "id=" + id + "initializationDate=" + initializationDate;
  }
  
}
