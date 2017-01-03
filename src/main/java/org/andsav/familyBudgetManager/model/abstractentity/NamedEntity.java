package org.andsav.familyBudgetManager.model.abstractentity;


/**
 * The {@code NamedEntity} abstract class is intended to be derived by
 * classes which objects state is to be persisted. Comprises following fields:
 * 
 * <blockquote><pre>
 * {@code Long} id,
 * {@code Date} creationDate,
 * {@code String} name
 * <pre></blockquote><p> 
 * 
 * <p>Inhritance chain: {@code NamedEntity -> DateInitUpdateEntity -> BaseEntity -> Object}
 * 
 * 
 * @See {@link org.andsav.familyBudgetManager.model.abstractentity.BaseEntity}
 * @See {@link org.andsav.familyBudgetManager.model.abstractentity.DateInitUpdateEntity}
 * 
 * @author asavka
 *
 */
public abstract class NamedEntity extends DateInitUpdateEntity{
	
	protected final String name;

	public NamedEntity() {
		this.name = "unnamed entity";
	}

	public NamedEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return super.toString() + "name=" + name;
	}
	

}
