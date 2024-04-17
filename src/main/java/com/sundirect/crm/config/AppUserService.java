package com.sundirect.crm.config;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sundirect.crm.bean.AppUser;
import com.sundirect.crm.bean.UserSignUp;


public interface AppUserService extends UserDetailsService {

	//void save(AppUser user);

	List<AppUser> fetchUsers();

	AppUser findUsername(String username);

	AppUser getCurrentUser();
	
	String userSignUp(UserSignUp details);
	

}
