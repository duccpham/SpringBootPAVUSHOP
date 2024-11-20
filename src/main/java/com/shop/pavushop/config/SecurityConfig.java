package com.shop.pavushop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.shop.pavushop.service.pavushopService;


@Configuration

public class SecurityConfig {
	
	
	@Bean
    UserDetailsService userDetailsService() {
        return new pavushopService();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	  }
	
	@Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
	 @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
			.requestMatchers("/checkOut").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
			.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
			.requestMatchers("/", "/login", "/logout","/resources/**").permitAll().anyRequest().permitAll()
			)
			.formLogin(formLogin -> formLogin
					.loginPage("/login")
	                .loginProcessingUrl("/doLogin")
	                .defaultSuccessUrl("/?login_success")
	                .successHandler(new SuccessHandler())
	                .failureUrl("/login?error=true")
	                .usernameParameter("customerId")
	                .passwordParameter("password")
	                )
			.logout((logout) -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/"));
			
		return http.build();
	 }
}






