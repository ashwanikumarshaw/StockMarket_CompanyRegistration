package com.example.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumingController {
	@PostMapping("/getLogin")
	public ResponseEntity<?> consumeLogin() throws Exception
	{
		RestTemplate restTemplate = new RestTemplate();
		
		String baseUrl = "http://localhost:8091/auth/user/login";
		
		ResponseEntity<String> response = null;
		
		try
		{
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
			//response = restTemplate.postForEntity(baseUrl, HttpMethod.POST, String.class);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Response object is null", HttpStatus.NO_CONTENT);
		
	}
	
	private static HttpEntity<?> getHeaders() throws Exception
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		//headers.set("Authorization", jwtToken);
		//headers.set("Access-Controll-Allow-Origin", "*");
		return new HttpEntity<>(headers);
		
	}

}