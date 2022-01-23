package com.springboot.dicr.country.security;

import org.springframework.stereotype.Service;

import com.springboot.dicr.country.model.RoleEntity;
import com.springboot.dicr.country.model.UserEntity;
import com.springboot.dicr.country.repository.IUserRepository;
import com.springboot.dicr.country.utils.BusinessMessages;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private IUserRepository userRepository;

	public CustomUserDetailsService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
				() -> new UsernameNotFoundException(BusinessMessages.USER_NOT_FOUND_WITH_USERNAME_OR_EMAIL + usernameOrEmail));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));

	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<RoleEntity> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
