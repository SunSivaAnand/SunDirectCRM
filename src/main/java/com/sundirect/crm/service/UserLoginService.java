package com.sundirect.crm.service;

import java.io.IOException;
import java.util.List;

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

	@Value("${user.info.type}")
	private String userInfo;
	
	public List<Login> userLoginDetails(){
		try {
			
            ObjectMapper mapper = new ObjectMapper();
            List<Login> userList = mapper.readValue(userInfo, new TypeReference<List<Login>>(){});           
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
		
	}
}
