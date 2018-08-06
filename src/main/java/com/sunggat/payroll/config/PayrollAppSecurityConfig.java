package com.sunggat.payroll.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@ComponentScan(basePackages="com.sunggat.payroll")
@EnableWebSecurity
public class PayrollAppSecurityConfig extends WebSecurityConfigurerAdapter {
	 
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private DataSource securityDataSource;
	
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/").hasAnyRole("MANAGER", "EMPLOYEE")
			.antMatchers("/user/showAddForm").hasRole("MANAGER")
			.antMatchers("/user/showUpdateForm").hasRole("MANAGER")
			.antMatchers("/benefit/showAddForm").hasRole("MANAGER")
			.antMatchers("/benefit/showUpdateForm").hasRole("MANAGER")
			.antMatchers("/department/showAddForm").hasRole("MANAGER")
			.antMatchers("/department/showUpdateForm").hasRole("MANAGER")
			.antMatchers("/job/showAddForm").hasRole("MANAGER")
			.antMatchers("/job/showUpdateForm").hasRole("MANAGER")
			.antMatchers("/payroll/showAddForm").hasRole("MANAGER")
			.antMatchers("/payrol/showUpdateForm").hasRole("MANAGER")
			.antMatchers("/performance/showAddForm").hasRole("MANAGER")
			.antMatchers("/performance/showUpdateForm").hasRole("MANAGER")
			.antMatchers("/userDetails/showAddForm").hasRole("MANAGER")
			.antMatchers("/userDetails/showUpdateForm").hasRole("MANAGER")
			.and()
			.formLogin()
				.loginPage("/loginPage")
				.loginProcessingUrl("/login")
				.usernameParameter("id").passwordParameter("password")
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
	
	 @Bean
	    public PersistentTokenRepository persistentTokenRepository() {
	        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	        db.setDataSource(securityDataSource);
	        return db;
	    }
	
}
