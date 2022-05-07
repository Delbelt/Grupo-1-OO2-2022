package app.services.implementation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.repositories.IUserRepository;
import lombok.var;

@Service("userDetailsService") // Importante respetar el nombre e implementar de UserDetailsService
public class userDetailsService implements UserDetailsService {
	
	@Autowired
	private IUserRepository repository;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		app.entities.User user = repository.findByUserName(username); // Verifica si existe en la BD
		
		if(user == null) throw new UsernameNotFoundException("User not found: " + user);
		
		var roles = new ArrayList<GrantedAuthority>(); // Spring solicita que el objeto sea del tipo: GrantedAuthority
		
		roles.add(new SimpleGrantedAuthority(user.getRole().getName()));		
		
		return new User(user.getUserName(), user.getPassword(), user.isEnabled(), true, true, true, roles); // User es una clase de Spring Segurity		 
	}
}
