package com.luxury.api.controller;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestController {

	protected final Logger logger = Logger.getLogger(getClass());

	protected String objectToJson(Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(obj);
			return json;
		} catch (JsonProcessingException jex) {
			logger.error(jex);
		}
		return null;
	}
}
