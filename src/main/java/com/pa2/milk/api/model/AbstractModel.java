package com.pa2.milk.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractModel<PK extends Serializable> {
	public abstract PK getId();

	public abstract void setId(PK id);

	/**
	 * Atributo de controle para exclusão lógica
	 * TRUE  = active
	 * FALSE = inactive
	 */
	@Column
	private Boolean active = true;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractModel<?> other = (AbstractModel<?>) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	@JsonIgnore
	public Boolean getActive() {
		return active;
	}

	@JsonIgnore
	public Boolean isActive(){
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
