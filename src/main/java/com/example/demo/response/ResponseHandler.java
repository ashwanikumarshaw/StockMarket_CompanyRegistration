package com.example.demo.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj)
	{
		Map <String, Object> mapObj = new HashMap<String, Object>();
		
		mapObj.put("Message", message);
		mapObj.put("Status", status);
		mapObj.put("Data", responseObj);
		
		return new ResponseEntity<Object>(mapObj, status);
	}
}
