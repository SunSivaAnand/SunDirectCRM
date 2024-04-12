package com.sundirect.crm.config;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;
import com.sundirect.crm.bean.AppUser;
import com.sundirect.crm.bean.Login;
import com.sundirect.crm.service.UserLoginService;



@Component
public class AppUserServiceImpl implements AppUserService {

	private static final Logger log = LoggerFactory.getLogger(AppUserServiceImpl.class);


	@Autowired
	UserLoginService userLoginService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login user = new Login();
		List<Login> userList=new ArrayList<Login>();
		userList=userLoginService.userLoginDetails();
		for(Login login:userList) {
			if(login.getUsername().equals(username)) {
				user=login;
				break;
			}else {
				user=null;
			}
		}
		//Login user = userRepo.findByUsername(username);
		//log.info("user.............." + user.getTenantName() + "::" + user.getUsername() + "::" + user.getRole().toString());
		Set<GrantedAuthority> grantAuth = new HashSet<>();
		grantAuth.add(new SimpleGrantedAuthority(user.getRole()));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantAuth);
	}

	/*
	 * @Override public void save(AppUser appUser) { Login user = new Login();
	 * 
	 * user.setUsername(appUser.getUsername());
	 * user.setPassword(passwordEncoder.encode(appUser.getPassword()));
	 * user.setTenantName(appUser.getTenantName()); log.info("appuser tenant name" +
	 * appUser.getTenantId());
	 * 
	 * Optional<Tenant> tent =
	 * tenantRepository.findByTenantName(appUser.getTenantName()); if
	 * (tent.isPresent()) { Tenant optent = tent.get();
	 * log.info("optnt in appuserservice" + optent.getId());
	 * 
	 * user.setTenantId(optent.getTenantId()); log.info("userid" +
	 * user.getTenantId()); user.setRoles(appUser.getRoles().stream().map(temp ->
	 * new Role(temp, user)).collect(Collectors.toSet())); userRepo.save(user); } }
	 */

	@Override
	public List<AppUser> fetchUsers() {
		List<AppUser> users = new ArrayList<>();
		List<Login> userRepo=new ArrayList<Login>();
		userRepo=userLoginService.userLoginDetails();
		
		userRepo.forEach(u -> users.add(getModelFromEntity(u)));
		return users;
	}

	private AppUser getModelFromEntity(Login u) {
		AppUser appUser = new AppUser();
		appUser.setUsername(u.getUsername());
		appUser.setPassword(u.getPassword());
		appUser.setTenantId(u.getTenantId());
		appUser.setId(String.valueOf(u.getId()));
		appUser.setRoles(u.getRole());
		return appUser;
	}

	@Override
	public AppUser findUsername(String username) {
		//log.info("Inside findUsername: "+username);
		if(null == username)	
		return null;
		List<Login> userList=new ArrayList<Login>();
		userList=userLoginService.userLoginDetails();
		//log.info("Inside findUsername list size: "+userList.size());
		for(Login login:userList) {
			if(login.getUsername().equals(username)) {
				return getModelFromEntity(login);
			}
		}
		
		return null;
	}

	@Override
	public AppUser getCurrentUser() {
		AppUser appUser = null;
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (auth != null) {
			//log.info("auth name: {}",auth.getName());
			String userName = auth.getName();
			//String userName ="Sun Direct";
			try {
			//log.info("checking;;;;;;;;;;;;;;;;;;"+userName);
			appUser = findUsername(userName);
			return appUser;
			}
			catch (Exception e) {
				return null;
			}
		}
		return appUser;
	}

}
