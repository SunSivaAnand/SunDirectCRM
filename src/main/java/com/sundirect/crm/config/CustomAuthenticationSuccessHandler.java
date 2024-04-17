package com.sundirect.crm.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sundirect.crm.bean.AppUser;
import com.sundirect.crm.controller.SMSController;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
	
	@Autowired
    SMSController controller;
	
	@Autowired
	AppUserService appUserService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.info("Logged in successfully...!");
		String username = request.getParameter("username");
		AppUser user = appUserService.findUsername(username);
		HttpSession session = request.getSession();
	        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        log.info("authuser" +authUser);
	        session.setAttribute("username", authUser.getUsername());
	        session.setAttribute("authorities", authentication.getAuthorities());
	        session.setAttribute("tenantId", user.getTenantId());       
	        response.setStatus(HttpServletResponse.SC_OK);	 	       
	        response.sendRedirect("/sms/subscriber/info");
	        
	       
	}
}
