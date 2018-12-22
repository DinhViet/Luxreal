package com.luxury.persistence.dao;

import com.luxury.persistence.model.CallTracker;

public interface ICallTrackerDao {

	boolean createCallTracker(CallTracker request);
	
	boolean updateCallTracker(CallTracker request);
	
	CallTracker getCallTracker(String phoneNumber,String productId);
	
}
