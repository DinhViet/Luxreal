package com.luxury.framework.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.luxury.framework.persistence.model.interfaces.CreatableEntity;

@MappedSuperclass
public abstract class DbEntry implements CreatableEntity, Serializable {
	private static final long serialVersionUID = 1L;
	public static final int LENGTH_CHAR_ONE = 1;
	public static final int LENGTH_CHAR_TWO = 2;
	public static final int LENGTH_CHAR_THREE = 3;
	public static final int LENGTH_STRING_SMALL = 6;
	public static final int LENGTH_STRING_MEDIUM = 80;
	public static final int LENGTH_STRING_LARGE = 200;
	public static final int LENGTH_STRING_HUGE = 2048;

	public static final Character PBX_TRUE = Character.valueOf('Y');
	public static final Character PBX_FALSE = Character.valueOf('N');
	
	@Basic(optional = true)
	@Column(name = "DB_ACTIVE", nullable = true)
	protected Character dbActive = PBX_TRUE;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = true)
	@Column(name = "CREATED_TIME", nullable = true, updatable = false)
	private Date creationDate;

	@Basic(optional = true)
	@Column(name = "CREATOR_ID", nullable = true, updatable = false)
	private Long creator;

	public String toString() {
		StringBuilder builder = new StringBuilder(128);
		builder.append(super.getClass().getSimpleName());
		Date creation = getCreationDate();
		if (creation != null) {
			builder.append("; created on ");
			builder.append(creation);
		} else {
			builder.append("; not yet persisted");
		}
		return builder.toString();
	}

	public Date getCreationDate() {
		if (this.creationDate == null) {
			return null;
		}
		return new Date(this.creationDate.getTime());
	}

	public boolean isSetCreationDate() {
		return (this.creationDate != null);
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = new Date(creationDate.getTime());
	}

	public Long getCreator() {
		return this.creator;
	}

	public boolean isSetCreator() {
		return (this.creator != null);
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}
	
	public boolean isActive() {
		return this.dbActive==null?true:PBX_TRUE.equals(this.dbActive);
	}
	
	public void setActive(boolean active) {
		this.dbActive = active ? PBX_TRUE : PBX_FALSE;
	}
	
	public Character getDbActive() {
		return dbActive;
	}
	
	public void setDbActive(Character dbActive) {
		this.dbActive = dbActive;
	}
}