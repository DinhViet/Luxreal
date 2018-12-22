package com.luxury.endpoint.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.luxury.common.ErrorMessages;
import com.luxury.endpoint.ICallTrackerService;
import com.luxury.model.CallTrackerRequest;
import com.luxury.model.Status;
import com.luxury.persistence.dao.ICallTrackerDao;
import com.luxury.persistence.dao.ILuxuryUserDao;
import com.luxury.persistence.model.CallTracker;
import com.luxury.persistence.model.User;

public class CallTrackerServiceImpl implements ICallTrackerService{

	@Autowired
	ICallTrackerDao callTrackerDao;
	
	@Autowired
	ILuxuryUserDao userDao;
	
	@Override
	public Status callTracker(CallTrackerRequest request) throws Exception {
		Status status = new Status();
		if(StringUtils.isEmpty(request.getPhoneNumber()) || StringUtils.isEmpty(request.getToken())){
			status.setRespCode(ErrorMessages.INVALID_PARAM.code);
			status.setDescription(ErrorMessages.INVALID_PARAM.message);
			return status;
		}
		
		User user = userDao.getDetail(request.getToken());
		if(user == null){
			status.setRespCode(ErrorMessages.INVALID_PARAM.code);
			status.setRespCode(ErrorMessages.INVALID_PARAM.message);
			return status;
		}
		
		CallTracker callTracker = callTrackerDao.getCallTracker(request.getPhoneNumber(),request.getProductId());
		if(callTracker != null){
			int callNumber =callTracker.getNumberCall() + 1;
			callTracker.setNumberCall(callNumber);
			callTracker.setLastUpdate(new Date());
			callTracker.setToken(request.getToken());
			callTrackerDao.updateCallTracker(callTracker);
		}else{
			callTracker = new CallTracker();
			callTracker.setToken(request.getToken());
			callTracker.setNumberCall(1);
			callTracker.setProductId(request.getProductId());
			callTracker.setCreationDate(new Date());
			callTracker.setLastUpdate(new Date());
			callTrackerDao.createCallTracker(callTracker);
		}
		status.setRespCode(ErrorMessages.SUCCESS.code);
		status.setDescription(ErrorMessages.SUCCESS.message);
		
		return status;
	}
	
	

}
