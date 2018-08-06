
package com.sunggat.payroll.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sunggat.payroll.dao.UserDAO;
import com.sunggat.payroll.entity.User;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserDAO userDao;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = this.userDao.findUserAccount(Integer.valueOf(id));
		UserBuilder builder = null;
		if(user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(id);
			builder.disabled(!user.isEnabled());
			builder.password(user.getPassword());
			String[] authorities = user.getAuthorities()
			          .stream().map(a -> a.getAuthority()).toArray(String[]::new);

			builder.authorities(authorities);
		} else {
			throw new UsernameNotFoundException("User with " + id + " id was not found");
		}
		
		return builder.build();
	}
	
	
}
