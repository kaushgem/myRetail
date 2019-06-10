package com.target.myRetail.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;
import com.target.myRetail.exceptions.NotFoundException;

import static com.target.myRetail.util.Constants.REDSKY_PRODUCT_TITLE_JSONPATH;

@Service
public class RedSkyService {
	private final static Logger LOG = LoggerFactory.getLogger(RedSkyService.class);
	
	private RestTemplate restTemplate;

	@Autowired
	public RedSkyService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Value("${application.redsky.url.get.id}")
	private String REDSKY_URL;
	
	public String getProductName(Long id) {
		Map<String, Long> uriVariables = new HashMap<>();
		uriVariables.put("id", id);
		ResponseEntity<String> entity;
		try {
			entity = restTemplate.getForEntity(REDSKY_URL, String.class, uriVariables);
		} catch (HttpClientErrorException.NotFound e) {
			throw new NotFoundException(id);
		}
		return JsonPath.read(entity.getBody(), REDSKY_PRODUCT_TITLE_JSONPATH);
	}
}
