package com.sundirect.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sundirect.crm.apientity.MyplexUserDevice;
import com.sundirect.crm.apientity.MyplexUserUser;
import com.sundirect.crm.service.SubscriberService;

@RestController
@RequestMapping(value = "/sms/")
public class SMSController {
	private static final Logger log = LoggerFactory.getLogger(SMSController.class);
	
	@Autowired
	SubscriberService subsService;
	
	
	@GetMapping(value="subscriber/info")
	public String customerInfo(Model model,@RequestParam(value = "query", required = true) Optional<Integer> query, @RequestParam(value="requestType") Optional<String> requestType) {
		
		String inp="";		
		if(requestType.get().equalsIgnoreCase("UserID")) {
			 inp =String.valueOf(query.get());
		}else if(requestType.get().equalsIgnoreCase("MobileNo")) {
			 inp =String.valueOf(query.get());
		}else if(requestType.get().equalsIgnoreCase("SMC")){
			 inp =String.valueOf(query.get());
		}else {
			return "";
		}
		
		
		MyplexUserUser user=new MyplexUserUser();
		try {
		user=subsService.findUserInformation(inp,requestType.get());
		log.info("UserName: {}",user.getFirst());
		log.info("SMC: {}",user.getSmc());
		log.info("Mobile No: {}",user.getMobileNo());
		}
		catch (Exception e) {
			// TODO: handle exception
			log.info("Exception occured: ",e.getMessage());
		}
		model.addAttribute(user);
		return "subscriber";
		
	}
	
	@GetMapping(value="device")
	public String getCustomerDevice(Model model, @RequestParam(value="userId",required = true) Integer userId) {
		
		List<MyplexUserDevice> deviceList=new ArrayList<MyplexUserDevice>();
		deviceList=subsService.findDeviceInfoByUserId(userId);		
		model.addAttribute("Device", deviceList);		
		int inc=0;
		for(MyplexUserDevice my:deviceList) {
			inc++;
			log.info("{} -  Device",inc);
			log.info("Os: {}",my.getOs());
			log.info("Os version: {}",my.getOs_version());
			log.info("Device make: {}",my.getMake());
			log.info("Model: {}",my.getModel());
		}
		
		return "device";
	}

}
