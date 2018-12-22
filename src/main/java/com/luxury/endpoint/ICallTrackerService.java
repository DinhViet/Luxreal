package com.luxury.endpoint;

import com.luxury.model.CallTrackerRequest;
import com.luxury.model.Status;

public interface ICallTrackerService {

	Status callTracker(CallTrackerRequest request)throws Exception;
	
}
