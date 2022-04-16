/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.controller.user;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.damtfg.IndustrialProcessManagement.exception.ResourceNotFoundException;
import es.damtfg.IndustrialProcessManagement.model.user.User;
import es.damtfg.IndustrialProcessManagement.repository.user.security.UserPrincipal;
import es.damtfg.IndustrialProcessManagement.security.CurrentUser;
import es.damtfg.IndustrialProcessManagement.service.user.UserServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * @author Alberto González
 *
 */
@RestController
@RequestMapping(ApiPath.USER)
public class UserController {

	@Autowired
	private UserServiceImpl userService;
			
	/**************************************************************************************
	 * Create methods
	 **************************************************************************************/
	
	// El usuario siempre irá asignado a otra entidad, por lo que se crea bajo demanda de las mismas.
	
	/**************************************************************************************
	 * Read methods
	 **************************************************************************************/
	
	/**
	 * Devuelve el usuario logueado
	 * 
	 * @param currentUser
	 * 
	 * @return Optional<User>
	 */
	@GetMapping("me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserPrincipal> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		
        return ResponseEntity.ok(currentUser);
    }
	
	/**
	 * Devuelve un usuario por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Usuario)
	 */
	@GetMapping("id/{id}")
	@PreAuthorize("hasRole('ADMIN') or principal.id == #id")
	public ResponseEntity<User> read(@PathVariable Long id) {
		
		User user = userService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user ID", id));
		
		return ResponseEntity.ok(user);
	}
	
	/**
	 * Devuelve un usuario por nombre de usuario
	 * 
	 * @param username
	 * 
	 * @return ResponseEntity (OK + Usuario)
	 */
	@GetMapping("{username}")
	@PreAuthorize("hasRole('ADMIN') or principal.username == #username")
	public ResponseEntity<?> read(@PathVariable String username) {
		
		User user = userService.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		
		return ResponseEntity.ok(user);
	}
	
	/**
	 * Devuelve todos los usuarios
	 * 
	 * @return Lista de usuarios
	 */
	@GetMapping("all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> readAll() {
		
		List<User> users = StreamSupport
				.stream(userService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return users;
	}
	
	/**************************************************************************************
	 * Update methods
	 **************************************************************************************/
	
	/**
	 * Actualiza usuario por ID
	 *  
	 * @param userDetails
	 * @param id
	 * 
	 * @return ResponseEntity (HTTP Status created + Usuario)
	 */
	@PutMapping(value = "{id}", consumes = {"application/json"})
	@PreAuthorize("hasRole('ADMIN') or principal.id == #id")
	public ResponseEntity<?> update(@Valid @RequestBody User userDetails, @PathVariable Long id) {
		
		User user = userService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user ID", id));
		
		user.setUsername(userDetails.getUsername());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
		userDetails.getPerson().setUser(user);
		user.setPerson(userDetails.getPerson());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
		
	/**************************************************************************************
	 * Delete methods
	 **************************************************************************************/
	
	/**
	 * Elimina un usuario por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK)
	 */
	@DeleteMapping("delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		userService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user ID", id));
		
		userService.deleteById(id);
		
		if (userService.findById(id).isPresent()) 
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		
		return ResponseEntity.ok().build();
	}
	
}
