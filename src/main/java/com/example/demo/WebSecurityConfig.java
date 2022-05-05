package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public  UserDetailsService userDetailsService()
	{
		return new CustomUserDetailsService();
	}

	@Bean
	public  BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public  DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/list_users").authenticated()
        .anyRequest().permitAll()
        .and().formLogin().loginPage("/login").permitAll()
            .usernameParameter("email")
            .defaultSuccessUrl("/register_success")
            .permitAll()
        .and()
        .logout().logoutSuccessUrl("/login").permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.inMemoryAuthentication().withUser("nirajasamineni90@gmail.com").password("niraja").authorities("ROLE_USER").and()
				.withUser("veerubhotlaharika@gmail.com").password("harika").authorities("ROLE_USER", "ROLE_ADMIN");
	}
	
	
	
	
}
