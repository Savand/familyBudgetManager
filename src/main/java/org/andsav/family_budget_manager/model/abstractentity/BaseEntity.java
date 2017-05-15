package org.andsav.family_budget_manager.model.abstractentity;

import org.andsav.family_budget_manager.util.date_convertor.LocalDateTimeAttributeConverter;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 * The {@code BaseEntity} abstract class is intended to be a superclass for classes which object's state is to be
 * persisted. Comprises following fields:
 * 
 * <blockquote>
 * 
 * <pre>
 * {@code Long} id, {@code Date} creationDate, {@code Date} lastUpdateDate,
 * 
 * <pre>
 * </blockquote>
 * <p>
 * 
 * 
 * @See {@link org.andsav.family_budget_manager.model.abstractentity.NamedEntity}
 * 
 * @author asavka
 *
 */

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class BaseEntity {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    protected Integer id;
    
    @Column(name = "creation_date", columnDefinition = "timestamp default now()")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    protected LocalDateTime creationDate;

    @Column(name = "last_update")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    protected LocalDateTime lastUpdate;

    public BaseEntity() {}

    public BaseEntity(Integer id) {
        this.id = id;

        if (id == null)
            this.creationDate = LocalDateTime.now();
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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 0 : getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;

        return null != getId() && getId().equals(that.getId());
    }

    @Override
    public String toString() {
        return "id=" + id + ", ";
    }

}
