package com.sundirect.crm.config;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sundirect.crm.bean.AppUser;


public interface AppUserService extends UserDetailsService {

	//void save(AppUser user);

	List<AppUser> fetchUsers();

	AppUser findUsername(String username);

	AppUser getCurrentUser();

}
