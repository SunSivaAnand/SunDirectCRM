package com.sundirect.crm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
public class SecurityConfig {

	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	@Configuration
	@Order(1)
	public static class ApiWebSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserDetailsService _userDetailsService;

		@Autowired
		PasswordEncoder _passwordEncoder;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			log.info("order1 configure2 method");

			http.csrf().disable().antMatcher("/api/**").httpBasic().disable().authorizeRequests().antMatchers("/api/**")
					.permitAll().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			//http.csrf().disable().antMatcher("/sms/**").httpBasic().disable().authorizeRequests().antMatchers("/sms/**")
			//.permitAll().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
	}

	@Configuration
	@Order(2)
	public static class FormLoginWebSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserDetailsService _userDetailsService;

		@Autowired
		private CustomAuthenticationSuccessHandler successHandler;

		@Autowired
		PasswordEncoder _passwordEncoder;

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			log.info("order2 configure1 method");

			DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
			authenticationProvider.setUserDetailsService(_userDetailsService);
			authenticationProvider.setPasswordEncoder(_passwordEncoder);

			auth.userDetailsService(_userDetailsService);
			auth.authenticationProvider(authenticationProvider);

		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			log.info("order2 configure2 method");
			
			http.authorizeRequests()
					.antMatchers("/login/**")
					.permitAll().antMatchers("/tenant").hasRole("ADMIN")
					.antMatchers("/ck/*").permitAll()
					.antMatchers("/subscriber/subscription/*/order", "/asset/*/bundle", "/bundle/*/plan").permitAll()
					.antMatchers(HttpMethod.GET, "/other").hasRole("ADMIN")
					.anyRequest().authenticated()
					.antMatchers(HttpMethod.PUT, "/coupon/*").hasRole("ADMIN").antMatchers(HttpMethod.POST, "/coupon")
					.hasRole("ADMIN").antMatchers(HttpMethod.POST, "/discount").hasRole("ADMIN")
					.anyRequest()
					.authenticated().and().formLogin().loginPage("/login/form").loginProcessingUrl("/login")
					.usernameParameter("username").passwordParameter("password").successHandler(successHandler)
					.failureUrl("/login/form?error").and().httpBasic().disable().logout().invalidateHttpSession(true)
					.clearAuthentication(true).logoutUrl("/logout").logoutSuccessUrl("/login/form?logout");
						

		}
		
	}
	
}