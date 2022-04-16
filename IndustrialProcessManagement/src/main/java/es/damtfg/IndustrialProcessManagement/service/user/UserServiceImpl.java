/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.user.User;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.user.UserRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import lombok.NonNull;

/**
 * @author Alberto González
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Creación de una nueva persona con su usuario.
	 * 
	 * @param User
	 * 
	 * @return ApiResponse
	 */
	@Override
	@Transactional
	public ApiResponse create(User user) {
		
		user.setEmail(user.getEmail().trim().toLowerCase());
		
		if (existsByUsername(user.getUsername())) {
			
			return new ApiResponse(false, AppMessages.ERROR_USERNAME_EXIST);
		}
		
		if (existsByEmail(user.getEmail())) {
			
			return new ApiResponse(false, AppMessages.ERROR_EMAIL_EXIST);
		}
				
		// Encode password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return new ApiResponse(true, AppMessages.SUCCESS_USER_CREATION);
	}
	
	/**
	 * Búsqueda de todos los usuarios.
	 * 
	 * @return Iterable<User>
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		
		return userRepository.findAll();
	}
	
	/**
	 * Búsqueda de todos los usuarios con paginación.
	 * 
	 * @param pageable
	 * 
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		
		return userRepository.findAll(pageable);
	}

	/**
	 * Búsqueda de usuario por ID.
	 * 
	 * @param id
	 * 
	 * @return Objeto Optional<User>
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
	
		return userRepository.findById(id);
	}
	
	/**
	 * Búsqueda de usuario por nombre de usuario.
	 * 
	 * @param username
	 * 
	 * @return Objeto Optional<User>
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<User> findByUsername(String username) {
	
		return userRepository.findByUsername(username);
	}

	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param user
	 * 
	 * @return Objeto usuario guardado
	 */
	@Override
	@Transactional
	public User save(User user) {
	
		return userRepository.save(user);
	}

	/**
	 * Elimina un usuario por ID.
	 * 
	 * @param id
	 */
	@Override
	@Transactional
	public void deleteById(Long id) {
		
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Boolean existsByUsername(@NonNull String username) {
		
		return userRepository.existsByUsername(username);
	}

	@Override
	@Transactional
	public Boolean existsByEmail(@NonNull String email) {
		
		return userRepository.existsByEmail(email);
	}
	
}
