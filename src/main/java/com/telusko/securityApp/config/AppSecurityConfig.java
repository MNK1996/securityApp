package com.telusko.securityApp.config;

import java.util.ArrayList;
import java.util.List;

//import com.telusko.securityApp.config.OurUserInfoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter  {
//
//	@Autowired
//	private UserDetailsService userDetailsService;

////
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new WebsecurityConfig();
//    }


//    @Bean
//	public AuthenticationProvider authProvider() {
//
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
//		provider.setUserDetailsService(userDetailsService);
////		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//		provider.setPasswordEncoder(new BCryptPasswordEncoder());
//
//
//		return provider;
//
//	}


	@Bean
    @Override
	protected UserDetailsService userDetailsService() {

		List<UserDetails> users = new ArrayList<>();
		users.add(User.withDefaultPasswordEncoder().username("navin")
				.password("1234").roles("USER","ADMIN").build());
		users.add(User.withDefaultPasswordEncoder().username("neel")
				.password("1234").roles("USER").build());
		users.add(User.withDefaultPasswordEncoder().username("vaar")
				.password("1234").roles("MANAGER").build());

		return new InMemoryUserDetailsManager(users);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers(HttpMethod.POST).hasAnyRole("ADMIN","MANAGER")
				.antMatchers(HttpMethod.PUT).hasAnyRole("ADMIN","MANAGER","USER")
				.antMatchers(HttpMethod.DELETE).hasAnyRole("MANAGER")
				.antMatchers(HttpMethod.GET,"/admin/getAllUsers").hasAnyRole("USER","ADMIN","MANAGER");
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();

		super.configure(http);
	}



}
