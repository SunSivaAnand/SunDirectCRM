package com.sundirect.crm.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sundirect.crm.bean.Login;
import com.sundirect.crm.config.AppUserServiceImpl;

@Component
public class UserLoginService {
	
	private static final Logger log = LoggerFactory.getLogger(UserLoginService.class);

	/*
	 * @Value("${user.info.type}") private String userInfo;
	 */
	
	@Value("${user.info.file}")
	private String file;
	
	public List<Login> userLoginDetails(){
		try {			
            ObjectMapper mapper = new ObjectMapper();
            String userDetails=mapper.writeValueAsString(mapper.readTree(new File(file)));            
            List<Login> userList = mapper.readValue(userDetails, new TypeReference<List<Login>>(){});           
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
		
	}
	
	public String userSignUp(String details,String fileName,String prev) {
		
		try{
		JSONObject json=new JSONObject(details);	
		String userName=json.getString("name");
		log.info("userName: {}",userName);		
		if(prev.contains(userName)) {
			log.info("user already available");
			return "user already exist";
		}			
		String out=prev.replace("]", "")+","+details+"]";		
		try(FileWriter writer= new FileWriter(fileName)){
			writer.write(out);
			log.info("user sign up success");
			return "success";
		}
		catch (Exception e) {
			log.info("Failed due to Exception {}",e.getMessage());
			e.printStackTrace();
			return "failed";
		}
		}
		catch (Exception e) {log.info("Failed due to Exception {}",e.getMessage());
		e.printStackTrace();
		return "failed";
		}		
	}
}
