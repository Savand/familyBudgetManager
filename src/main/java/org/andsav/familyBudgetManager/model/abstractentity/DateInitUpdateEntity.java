package org.andsav.familyBudgetManager.model.abstractentity;

import java.time.LocalDateTime;


/**
 * The {@code DateInitEntity} abstract class is intended to be derived by
 * classes which objects state is to be persisted. Comprises following fields:
 * 
 * <blockquote><pre>
 * {@code Long} id,
 * {@code Date} creationDate,
 * <pre></blockquote><p> 
 * 
 * <p>Inhritance chain: {@code DateInitUpdateEntity -> BaseEntity -> Object}
 * 
 * 
 * @See {@link org.andsav.familyBudgetManager.model.abstractentity.BaseEntity}
 * @See {@link org.andsav.familyBudgetManager.model.abstractentity.DateInitUpdateEntity}
 * 
 * @author asavka
 *
 */
public abstract class DateInitUpdateEntity extends BaseEntity {
  
  protected final LocalDateTime initializationDate;
  protected LocalDateTime lastUpdateDate;

  public DateInitUpdateEntity() {
    initializationDate = LocalDateTime.now();
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

  @Override
  public String toString() {
    return "initializationDate=" + initializationDate + ", lastUpdateDate=" + lastUpdateDate;
  }
  
  
}
