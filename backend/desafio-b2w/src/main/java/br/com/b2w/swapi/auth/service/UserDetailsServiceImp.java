package br.com.b2w.swapi.auth.service;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.b2w.swapi.util.Constantes;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(Constantes.USER_NOLOGIN);
		builder.disabled(Boolean.FALSE);
		builder.password(Constantes.USER_NOLOGIN);
		String[] authorities = { Constantes.AUTH_NOLOGIN };
		builder.authorities(authorities);
		String[] roles = { Constantes.ROLE_NOLOGIN };
		builder.roles(roles);
		builder.accountExpired(Boolean.FALSE);
		builder.accountLocked(Boolean.FALSE);
		return builder.build();
	}
}
