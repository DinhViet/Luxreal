package com.luxury.framework.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import com.luxury.framework.persistence.model.interfaces.IdEntity;

@MappedSuperclass
public abstract class NoneUpdatableGeneratedIdEntry extends DbEntry implements IdEntity<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_ENTITY")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idSequence")
    @SequenceGenerator(name = "idSequence", sequenceName = "SEQ_ID_ENTITY", allocationSize = 50)
    private Long id;

    public String toString() {
	StringBuilder buf = new StringBuilder();
	buf.append(super.getClass().getSimpleName());
	Long dummy = this.id;
	if (dummy == null) {
	    buf.append(" [not yet persisted]");
	} else {
	    buf.append(" #");
	    buf.append(dummy);
	}
	return buf.toString();
    }

    public boolean equals(Object o) {
	if (o == null) {
	    return false;
	}
	if (this.id == null) {
	    return false;
	}
	if (!(super.getClass().isAssignableFrom(o.getClass()))) {
	    return false;
	}

	NoneUpdatableGeneratedIdEntry other = (NoneUpdatableGeneratedIdEntry) o;

	return this.id.equals(other.getId());
    }

    public int hashCode() {
	return ((this.id == null) ? 0 : this.id.hashCode());
    }

    public Long getId() {
	return this.id;
    }

    public boolean isSetId() {
	return (this.id != null);
    }
}