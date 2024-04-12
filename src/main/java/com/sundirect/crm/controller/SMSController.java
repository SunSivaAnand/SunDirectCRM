package com.sundirect.crm.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sundirect.crm.bean.AppUser;
import com.sundirect.crm.bean.Asset;
import com.sundirect.crm.bean.DeviceInfo;
import com.sundirect.crm.bean.Login;
import com.sundirect.crm.bean.MapObject;
import com.sundirect.crm.bean.MyplexUserUser;
import com.sundirect.crm.bean.Plan;
import com.sundirect.crm.bean.PlayerEvent;
import com.sundirect.crm.bean.PlayerEvent.Fields;
import com.sundirect.crm.bean.SDPlan;
import com.sundirect.crm.bean.UserInfo;
import com.sundirect.crm.config.AppUserService;
import com.sundirect.crm.service.APIService;
import com.sundirect.crm.service.SubscriberService;

@Controller
public class SMSController {

	private static final Logger log = LoggerFactory.getLogger(SMSController.class);

	@Autowired
	AppUserService appUserService;

	@Autowired
	APIService apiService;

	@Value("${application.version}")
	private String version;

	@GetMapping(value = "/api/Allplans")
	public String getAllPlan(Model model, @RequestParam(name = "status") String status, HttpServletRequest request) {

		try {
			String returnVal = apiService.getAllPlanAPI(status);
			// JSONObject jsonObj=new JSONObject(returnVal);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(returnVal);
			log.info("json string: {}", jsonNode.toString());
			model.addAttribute("planValue", jsonNode);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception occur: {}", e.getMessage());

		}
		return "plan";
	}

	@GetMapping(value = "/sms/subscriber/info")
	public String customerInfo(Model model, @RequestParam(value = "query", required = true) Optional<String> query,
			@RequestParam(value = "requestType") Optional<String> requestType) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getAuthorities().stream()
		        .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
			//log.info("say yess...................");
			model.addAttribute("showButtom", true);
			
		}else {
			//log.info("say noooooooo...................");
			model.addAttribute("showButtom", false);
		}
		
		Integer userId = 0;
		String param = "";
		if (query.isPresent() && requestType.isPresent() && !query.get().isEmpty()) {
			//log.info("Entering into impl=====22222");
			String inp = "";
			if (requestType.get().equalsIgnoreCase("UserID")) {
				inp = String.valueOf(query.get());
				param = "user_id";
			} else if (requestType.get().equalsIgnoreCase("MobileNo")) {
				inp = String.valueOf(query.get());
				param = "rmn";
			} else if (requestType.get().equalsIgnoreCase("SMC")) {
				inp = String.valueOf(query.get());
				param = "smc";
			}
			try {
				String resp = apiService.getUserInfo(query.get(), userId, param);
				JSONObject jsonObj = new JSONObject(resp);
				String responseFinal = jsonObj.getString("user_info");
				List<UserInfo> userInfoList = new ArrayList<UserInfo>();
				try {
					//log.info("test: {}", resp.trim());

					ObjectMapper mapper = new ObjectMapper();
					userInfoList = mapper.readValue(responseFinal, new TypeReference<List<UserInfo>>() {
					});
					log.info("checking userInfo.....{}", userInfoList.get(0).getFields().getFirst());
				} catch (IOException e) {
					e.printStackTrace();
				}
				model.addAttribute("user", userInfoList);
				model.addAttribute("query", String.valueOf(query.get()));
				model.addAttribute("filter", requestType.get());
			} catch (NoSuchElementException e) {
				log.info("User not found: {}", e.getMessage());
				model.addAttribute("message", "User not found");
			} catch (Exception e) {
				// TODO: handle exception
				log.info("Exception occured: {}", e.getMessage());
				e.printStackTrace();
				model.addAttribute("message", "Please Enter Valid Input");
			}
		} else {
			// model.addAttribute("query", String.valueOf(query.get()));
			// model.addAttribute("filter", requestType.get());
			model.addAttribute("message", "");
		}
		try {
			String returnVal = apiService.getAllPlanAPI("Active");
			JSONObject jsonObj2 = new JSONObject(returnVal);
			String responseFinal2 = jsonObj2.getString("results");
			List<Plan> smsplanList = new ArrayList<Plan>();
			try {
				//log.info("test: {}", returnVal.trim());
				ObjectMapper mapper = new ObjectMapper();
				smsplanList = mapper.readValue(responseFinal2, new TypeReference<List<Plan>>() {
				});
				// log.info("checking userInfo.....{}",smsplanList.get(0).getAction());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String returnValClosed = apiService.getAllPlanAPI("Closed");
			JSONObject jsonObj2Closed = new JSONObject(returnValClosed);
			String responseFinal2Closed = jsonObj2Closed.getString("results");
			List<Plan> smsplanListClosed = new ArrayList<Plan>();
			try {
				log.info("test: {}", returnValClosed.trim());
				ObjectMapper mapper = new ObjectMapper();
				smsplanListClosed = mapper.readValue(responseFinal2Closed, new TypeReference<List<Plan>>() {
				});
				// log.info("checking userInfo.....{}",smsplanList.get(0).getAction());
			} catch (IOException e) {
				e.printStackTrace();
			}
			log.info("checking userInfo closed before.....{}",smsplanListClosed.size());
			log.info("checking userInfo before.....{}",smsplanList.size());
			for(Plan pl:smsplanListClosed) {
				smsplanList.add(pl);
			}			
			log.info("checking userInfo after.....{}",smsplanList.size());
			String sdPlan = apiService.getAllSDPlan();
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

			//log.info("SD plan list size: {} sms plan list size: {}", planList.size(), smsplanList.size());
			final List<Plan> smsFinalList = smsplanList;
			List<SDPlan> finalList = new ArrayList<SDPlan>();
			finalList = planList.stream().filter(
					obj1 -> smsFinalList.stream().anyMatch(obj2 -> obj2.getPlanId() == obj1.getFields().getSmsPlanId()))
					.collect(Collectors.toList());

			// JSONObject jsonObj=new JSONObject(returnVal);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(returnVal);
			// log.info("json string: {}",jsonNode.toString());
			//log.info("SD plan list final size: {}", finalList.size());
			// return finalList;
			model.addAttribute("finalList", finalList);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception occur: {}", e.getMessage());
			return null;
		}
		return "subscriber";
	}

	@GetMapping(value = "/deviceInfo")
	public @ResponseBody Map<String, List<MapObject>> getCustomerDevice(Model model,
			@RequestParam(value = "userId", required = true) Integer userId) {
		Map<String, List<MapObject>> modelmap = new HashMap<String, List<MapObject>>();
		try {
			String resp = apiService.getUserInfo(userId + "", 1, "user_id");
			JSONObject jsonObj = new JSONObject(resp);
			String responseFinal = jsonObj.getString("device_info");
			List<DeviceInfo> deviceInfoList = new ArrayList<DeviceInfo>();
			try {
				//log.info("test: {}", resp.trim());

				ObjectMapper mapper = new ObjectMapper();
				deviceInfoList = mapper.readValue(responseFinal, new TypeReference<List<DeviceInfo>>() {
				});
				log.info("checking userInfo.....{}", deviceInfoList.get(0).getFields().getMake());
			} catch (IOException e) {
				e.printStackTrace();
			}
			log.info("Entering in Device Details........");
			// List<MyplexUserDevice> deviceList = new ArrayList<MyplexUserDevice>();
			List<MapObject> mapList = new ArrayList<MapObject>();
			// deviceList = subsService.findDeviceInfoByUserId(userId);
			model.addAttribute("deviceList", deviceInfoList);

			if (deviceInfoList.isEmpty()) {
				MapObject mapObject = new MapObject();
				mapObject.setMessage1("No Data available");
				mapList.add(mapObject);
				modelmap.put("deviceList", mapList);
				return modelmap;
			}
			model.addAttribute("appLaunch", deviceInfoList.size());
			int inc = 0;
			for (DeviceInfo my : deviceInfoList) {
				inc++;
				MapObject mapObject = new MapObject();
				mapObject.setInc(deviceInfoList.size());
				mapObject.setDevice(my);
				mapObject.setMessage("success");
				log.info("{} -  Device", inc);
				log.info("Os: {}", my.getFields().getOs());
				log.info("Os version: {}", my.getFields().getOs_version());
				log.info("Device make: {}", my.getFields().getMake());
				log.info("Model: {}", my.getModel());
				mapList.add(mapObject);
			}
			// MapObject mapObject=new MapObject();
			// mapList.add(mapObject);
			modelmap.put("deviceList", mapList);

		} catch (Exception e) {
			// TODO: handle exception
			log.info("Exception occured in device info: ", e.getMessage());
			model.addAttribute("message", "No Data available");
			MapObject mapObject = new MapObject();
			mapObject.setMessage("No Data available");
			List<MapObject> mapList = new ArrayList<MapObject>();
			mapList.add(mapObject);
			modelmap.put("message", mapList);
		}
		return modelmap;
	}

	@GetMapping(value = "/otherInfo")
	public @ResponseBody Map<String, List<MapObject>> getCustomerOtherInfo(Model model,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "startDate", required = false) Optional<String> startDate,
			@RequestParam(value = "endDate", required = false) Optional<String> endDate) {
		String formattedcurrentDate = "";
		String formattedToDay = "";
		if (startDate.equals(null) || endDate.equals(null)) {
			log.info("Checking Dates are null...........");
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			formattedcurrentDate = dateFormat.format(currentDate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date datebefore7Days = calendar.getTime();
			formattedToDay = dateFormat.format(datebefore7Days);
			log.info("Date is null  then  start date: {}, end date: {}", formattedToDay, formattedcurrentDate);
		} else {
			try {
				log.info("Checking Dates are not null...........");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				formattedToDay = dateFormat.format(dateFormat.parse(startDate.get()));
				formattedcurrentDate = dateFormat.format(dateFormat.parse(endDate.get()));
				log.info("Date is present then  start date: {}, end date: {}", formattedcurrentDate, formattedToDay);
			} catch (Exception e) {
				// TODO: handle exception
				log.info("Checking Dates are null...........");
				Date currentDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				formattedcurrentDate = dateFormat.format(currentDate);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(currentDate);
				calendar.add(Calendar.DAY_OF_MONTH, -7);
				Date dateAfter7Days = calendar.getTime();
				formattedToDay = dateFormat.format(dateAfter7Days);
				log.info("Date is null  then  start date: {}, end date: {}", formattedToDay, formattedcurrentDate);

			}
		}

		Map<String, List<MapObject>> modelmap = new HashMap<String, List<MapObject>>();
		try {
			String resp = apiService.getOtherInfo(userId + "", 2, "user_id", formattedToDay, formattedcurrentDate);
			JSONObject jsonObj = new JSONObject(resp);
			String responseFinal = jsonObj.getString("player_event");
			List<PlayerEvent> eventList = new ArrayList<PlayerEvent>();
			// log.info("otherInfo response: {}",responseFinal.trim());
			ObjectMapper mapper = new ObjectMapper();
			eventList = mapper.readValue(responseFinal, new TypeReference<List<PlayerEvent>>() {
			});
			log.info("checking OtherInfo.....{}", eventList.size());
			List<MapObject> mapList = new ArrayList<MapObject>();
			model.addAttribute("", eventList);
			if (eventList.isEmpty()) {
				MapObject mapObject = new MapObject();
				mapObject.setMessage1("No Data available");
				mapList.add(mapObject);
				modelmap.put("otherInfo", mapList);
				return modelmap;
			}
			List<String> assetList = eventList.stream().map(p -> p.getFields().getContentId())
					.collect(Collectors.toList());
			String assetResp = apiService.getAllAssetDetails(assetList);
			//log.info("Asset Resp: {}", assetResp);
			JSONObject assetjson = new JSONObject(assetResp);
			String assetResponse = assetjson.getString("results");
			List<Asset> assetInfoList = new ArrayList<Asset>();
			try {
				//log.info("test: {}", resp.trim());

				ObjectMapper mapper1 = new ObjectMapper();
				assetInfoList = mapper1.readValue(assetResponse, new TypeReference<List<Asset>>() {
				});
				log.info("asset info.....{}", assetInfoList.get(0).getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (PlayerEvent p : eventList) {
				MapObject mapObject = new MapObject();
				//log.info("Last 7 days Event List");
				Asset assetVal = new Asset();
				/*
				 * for(Asset asset:assetInfoList) {
				 * if(p.getFields().getContentId().equals(asset.getAssetId())) { assetVal=asset;
				 * } }
				 */

				assetVal = assetInfoList.stream()
						.filter(asset -> p.getFields().getContentId().equals(asset.getAssetId())).findFirst()
						.orElse(new Asset());
				log.info("Asset Details: {}", assetVal.getName());
				mapObject.setAsset(assetVal);
				mapObject.setMessage("success");
				log.info("Action: {}", p.getFields().getAction());
				mapObject.setPlayerEvent(p);
				mapList.add(mapObject);
			}
			modelmap.put("otherInfo", mapList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Exception occured in other info: ", e.getMessage());
			model.addAttribute("message", "No Player Event available");
			MapObject mapObject = new MapObject();
			mapObject.setMessage("No Player Event available");
			List<MapObject> mapList = new ArrayList<MapObject>();
			mapList.add(mapObject);
			modelmap.put("message", mapList);
		}

		return modelmap;
	}

	@GetMapping("/getAllLiveTv")
	public String getAllLiveTv(Model model, @RequestParam(value = "contentId", required = false) String contentId) {

		try {
			if(null==contentId || contentId.isEmpty()) {
			List<Asset> assetInfoList = new ArrayList<Asset>();
			String assetResp = apiService.getAllLiveAsset();
			//log.info("Asset Resp: {}", assetResp);
			JSONObject assetjson = new JSONObject(assetResp);
			String assetResponse = assetjson.getString("results");
			
			//log.info("test: {}", assetResp.trim());

			ObjectMapper mapper1 = new ObjectMapper();
			assetInfoList = mapper1.readValue(assetResponse, new TypeReference<List<Asset>>() {});
			log.info("asset info.....{}", assetInfoList.get(0).getName());		
			model.addAttribute("assetInfoList", assetInfoList);
			}else {
				List<Asset> assetInfoList = new ArrayList<Asset>();
				String assetResp = apiService.getAllLiveAsset();
				//log.info("Asset Resp: {}", assetResp);
				JSONObject assetjson = new JSONObject(assetResp);
				String assetResponse = assetjson.getString("results");
				
				//log.info("test: {}", assetResp.trim());

				ObjectMapper mapper1 = new ObjectMapper();
				assetInfoList = mapper1.readValue(assetResponse, new TypeReference<List<Asset>>() {});
				log.info("asset info.....{}", assetInfoList.get(0).getName());		
				model.addAttribute("assetInfoList", assetInfoList);
			String contentResp=apiService.getAllDetailBasedAsset(contentId);
			//log.info("Content Resp: {}", contentResp);
			JSONObject contentjson = new JSONObject(contentResp);
			String contentResponse = contentjson.getString("content_status");
			//ObjectMapper mapper1 = new ObjectMapper();
			List<PlayerEvent> contentInfoList = new ArrayList<PlayerEvent>();
			contentInfoList = mapper1.readValue(contentResponse, new TypeReference<List<PlayerEvent>>() {});
			//log.info("content info.....{}", contentInfoList.toString());
			long count=contentInfoList.stream().map(PlayerEvent::getFields).map(Fields::getUserId).distinct().count();	
			log.info("Distinct user: {} and listSzie: {}",count,contentInfoList.size());
			model.addAttribute("contentInfoList",contentInfoList);	
			model.addAttribute("DistinctUser",count);
			
			}					
			//return "live";			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "live";

	}

	@GetMapping("/login/form")
	public String login(Model model) {
		//log.info("model from /login/form" + model);
		AppUser user = appUserService.getCurrentUser();
		if (null == user) {
			//log.info("user" + user);
			model.addAttribute("version", version);
			return "login";
		}
		return "redirect:/";
	}
}