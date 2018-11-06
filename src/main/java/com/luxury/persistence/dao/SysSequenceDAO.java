package com.luxury.persistence.dao;

import com.luxury.framework.persistence.dao.GeneratedIdDAO;
import com.luxury.persistence.model.SysSequence;

public interface SysSequenceDAO extends GeneratedIdDAO<SysSequence> {
	Long getNextSequence(String sequence, Long actor);
}
