package com.example.security.ApplicationSecurityConfig;

import com.example.security.ApplicationUserRole.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		  .authorizeRequests().antMatchers("/", "index","/css/**").permitAll()
		  .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		  .anyRequest().authenticated().and().httpBasic();
		
		
	}
	
	@Override
	@Bean
		protected UserDetailsService userDetailsService() {
			UserDetails jameUser = User.builder()
			.username("James")
			.password(passwordEncoder.encode("password"))
			.roles(ApplicationUserRole.STUDENT.name())   //ROLE_STUDENT
			.build();
			UserDetails lindaUser=User.builder()
			.username("linda")
			.password(passwordEncoder.encode("password"))
			.roles(ApplicationUserRole.ADMIN.name())
			.build();
			return new InMemoryUserDetailsManager(jameUser, lindaUser);
		}
	
	}
