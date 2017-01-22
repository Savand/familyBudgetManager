package org.andsav.familyBudgetManager.model.abstractentity;

import java.time.LocalDateTime;

/**
 * The {@code BaseEntity} abstract class is intended to be a superclass for
 * classes which object's state is to be persisted. Comprises following fields:
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
  private LocalDateTime creationDate;
  private LocalDateTime lastUpdateDate;
  
  public BaseEntity() {
    
    if(isNew()){
      creationDate = LocalDateTime.now();
    } else {
      this.lastUpdateDate = LocalDateTime.now();
    }
    
  }

  public BaseEntity(Integer id) {

    if(isNew()){
      creationDate = LocalDateTime.now();
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
  
  public LocalDateTime getCreationDate() {
    return creationDate;
  }
  
  public LocalDateTime getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }
  
  public boolean isNew(){
    return id == null;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BaseEntity other = (BaseEntity) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "id=" + id + ", ";
  }
  
}
