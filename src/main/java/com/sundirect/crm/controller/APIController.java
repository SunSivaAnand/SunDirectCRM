package com.sundirect.crm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sundirect.crm.bean.OrderCreation;
import com.sundirect.crm.bean.OrderCreationAPI;
import com.sundirect.crm.bean.Plan;
import com.sundirect.crm.bean.SDPlan;
import com.sundirect.crm.bean.UserInfo;
import com.sundirect.crm.bean.UserSignUp;
import com.sundirect.crm.config.AppUserService;
import com.sundirect.crm.service.APIService;
import com.sundirect.crm.utils.ApiReturn;

@RestController
public class APIController {
	private static final Logger log = LoggerFactory.getLogger(APIController.class);
	@Autowired
	APIService apiservice;
	
	@Autowired
	AppUserService appUserService;

	@GetMapping(value = "/api/AllplansAPI")
	public List<SDPlan> getAllPlan(@RequestParam(name = "status") String status, HttpServletRequest request) {
		log.info("coming inside allplan");
		try {
			String returnVal = apiservice.getAllPlanAPI(status);
			JSONObject jsonObj2 = new JSONObject(returnVal);
			String responseFinal2 = jsonObj2.getString("results");
			List<Plan> smsplanList = new ArrayList<Plan>();
			try {
				// log.info("test: {}",returnVal.trim());
				ObjectMapper mapper = new ObjectMapper();
				smsplanList = mapper.readValue(responseFinal2, new TypeReference<List<Plan>>() {
				});
				log.info("checking userInfo.....{}", smsplanList.get(0).getAction());
			} catch (IOException e) {
				e.printStackTrace();
			}

			String sdPlan = apiservice.getAllSDPlan();
			JSONObject jsonObj = new JSONObject(sdPlan);
			String responseFinal = jsonObj.getString("results");
			List<SDPlan> planList = new ArrayList<SDPlan>();
			try {
				// log.info("test: {}",sdPlan.trim());
				ObjectMapper mapper = new ObjectMapper();
				planList = mapper.readValue(responseFinal, new TypeReference<List<SDPlan>>() {
				});
				log.info("checking userInfo 1.....{}", planList.get(0).getFields().getPlan_description());
			} catch (IOException e) {
				e.printStackTrace();
			}

			log.info("SD plan list size: {} sms plan list size: {}", planList.size(), smsplanList.size());
			final List<Plan> smsFinalList = smsplanList;
			List<SDPlan> finalList = new ArrayList<SDPlan>();
			finalList = planList.stream().filter(
					obj1 -> smsFinalList.stream().anyMatch(obj2 -> obj2.getPlanId() == obj1.getFields().getSmsPlanId()))
					.collect(Collectors.toList());
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(returnVal);
			log.info("SD plan list final size: {}", finalList.size());
			return finalList;

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception occur: {}", e.getMessage());
			return null;
		}

	}

	@PostMapping(value = "/api/v1/order", consumes = "application/json")
	public JsonNode createOrderSMS(@Valid @RequestBody OrderCreation model, HttpServletRequest request) {

		try {
			String returnVal = apiservice.orderCreation(model);
			// log.info("returnVal string: {}",returnVal);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(returnVal);
			// log.info("json string: {}",jsonNode.toString());
			return jsonNode;

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception occur: {}", e.getMessage());
			return null;
		}

	}

	@PostMapping(value = "/api/v1/apiOrder", consumes = "application/json")
	public JsonNode createOrderAPI(@Valid @RequestBody OrderCreationAPI model, HttpServletRequest request) {
		try {
			String returnVal = apiservice.orderCreationAPI(model);
			log.info("returnVal string: {}", returnVal);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(returnVal);
			log.info("json string: {}", jsonNode.toString());
			return jsonNode;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception occur: {}", e.getMessage());
			return null;
		}
	}

	@GetMapping(value = "/api/user/subscription/{userId}/{country}")
	public JsonNode getUserSubscription(@PathVariable(value = "userId") Integer userId,
			@PathVariable(value = "country") String country, HttpServletRequest request) {

		try {
			log.info("user Id: {} country: {}", userId, country);
			String returnVal = apiservice.getAllSubscription(userId, country);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(returnVal);
			log.info("json string: {}", jsonNode.toString());
			return jsonNode;

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception occur: {}", e.getMessage());
			return null;
		}
	}
	
	
	@PostMapping(value="/api/sms/signup", consumes = "application/json")
	public String signUp(@RequestBody UserSignUp user) {		
		
		String resp=appUserService.userSignUp(user);
				
		return resp;		
	}

}
