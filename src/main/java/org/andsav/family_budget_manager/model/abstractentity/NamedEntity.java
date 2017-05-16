package org.andsav.family_budget_manager.model.abstractentity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;


/**
 * The {@code NamedEntity} abstract class is intended to be derived by classes which named objects state is to be
 * persisted. Comprises following fields:
 * 
 * <blockquote>
 * 
 * <pre>
 * {@code Long} id, {@code Date} creationDateTime, {@code Date} lastUpdateDateTime, {@code String} name
 * 
 * <pre>
 * </blockquote>
 * <p>
 * 
 * <p>
 * Inhritance chain: {@code NamedEntity -> BaseEntity -> Object}
 * 
 * 
 * @See {@link org.andsav.family_budget_manager.model.abstractentity.BaseEntity}
 * 
 * @author asavka
 *
 */

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class NamedEntity extends BaseEntity {

    @Size(min=6)
    @Column(nullable = false)
    protected String name;

    public NamedEntity() {}

    public NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + "name=" + name + ", ";
    }


}
