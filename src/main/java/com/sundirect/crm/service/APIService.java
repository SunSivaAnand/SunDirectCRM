package com.sundirect.crm.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.sundirect.crm.bean.Asset;
import com.sundirect.crm.bean.OrderCreation;
import com.sundirect.crm.bean.OrderCreationAPI;

public interface APIService {

	String getAllPlanAPI(String status);
	
	String getAllSDPlan();
	
	String orderCreation(OrderCreation model);
	
	String orderCreationAPI(OrderCreationAPI model);
	
	String getAllSubscription(Integer userId, String country);
	
	String getUserInfo(String info,Integer keyVal,String param);
	String getOtherInfo(String info,Integer keyVal,String param,String startDate, String endDate);
	
	public String getAllAssetDetails(List<String> assets);
	
	String getAllLiveAsset();
	
	String getAllDetailBasedAsset(String contentId);
}
