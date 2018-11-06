package com.truemoney.walletgw.persistence.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.truemoney.framework.persistence.model.GeneratedIdEntry;

@Entity
@Table(name = "SYS_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "ID_SEQ"))
public class SysSequence extends GeneratedIdEntry {
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "STR_NAME", nullable = false, unique = true)
	private String name;
	@Basic(optional = false)
	@Column(name = "STR_VALUE", nullable = false)
	private Long value = 0L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}