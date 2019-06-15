package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.security.UserSS;

@Service
public class UserDetailsImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository cliente_repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente c= cliente_repo.findByEmail(username);
		if(c == null) {
			throw new UsernameNotFoundException("Não foi encontrado o e-mail");
		}
		
		return new UserSS(c.getId(),c.getEmail(),c.getPassword(),c.getPerfis());
	}

	

}
