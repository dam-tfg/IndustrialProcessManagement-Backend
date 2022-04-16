/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.service.user.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.damtfg.IndustrialProcessManagement.model.user.User;
import es.damtfg.IndustrialProcessManagement.repository.user.UserRepository;
import es.damtfg.IndustrialProcessManagement.repository.user.security.UserPrincipal;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * @author Alberto González
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) 
			throws UsernameNotFoundException {
		
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> 
					new UsernameNotFoundException(AppMessages.EXCEPTION_USERNAME_NOT_FOUND + usernameOrEmail)
		);
		
		return UserPrincipal.create(user);
	}
	
	/**
	 * Método usado por JWTAuthenticationFilter.
	 * 
	 * @param id
	 * 
	 * @return
	 */
	@Transactional
	public UserDetails loadUserById(Long id) {
		
		User user = userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException(AppMessages.EXCEPTION_USERNAME_ID_NOT_FOUND + id)
		);
		
		return UserPrincipal.create(user);
	}
	
}
