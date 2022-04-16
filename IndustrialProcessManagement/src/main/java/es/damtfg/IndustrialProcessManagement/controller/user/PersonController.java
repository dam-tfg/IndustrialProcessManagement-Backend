/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.controller.user;

import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.damtfg.IndustrialProcessManagement.exception.AppException;
import es.damtfg.IndustrialProcessManagement.exception.ResourceNotFoundException;
import es.damtfg.IndustrialProcessManagement.model.user.Person;
import es.damtfg.IndustrialProcessManagement.model.user.User;
import es.damtfg.IndustrialProcessManagement.model.user.security.Role;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.user.PersonSignUpRequest;
import es.damtfg.IndustrialProcessManagement.service.user.PersonServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.user.UserServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.user.security.RoleServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * @author Alberto González
 *
 */
@RestController
@RequestMapping(ApiPath.PERSON)
public class PersonController {

	@Autowired
	private PersonServiceImpl personService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RoleServiceImpl roleService;
		
	/**************************************************************************************
	 * Create methods
	 **************************************************************************************/
	
	/**
	 * Crea usuario persona.
	 * 
	 * @param signUpRequest
	 * 
	 * @return ResponseEntity (HTTP Status created + Location)
	 */
	@PostMapping("new")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody PersonSignUpRequest signUpRequest) {
				
		User newUser = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword(), new Date());
		
		ApiResponse apiResponse = userService.create(newUser);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Person person = new Person(signUpRequest.getName(), signUpRequest.getSurnames(), newUser);
		
		newUser.setPerson(person);
		
		Role userRole = roleService.findByName("ROLE_USER")
				.orElseThrow(() -> new AppException(AppMessages.EXCEPTION_USER_ROLE_NOT_SET));
		
		newUser.setRoles(Collections.singleton(userRole));
		
		User result = userService.save(newUser);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path(ApiPath.USER + "/{username}")
				.buildAndExpand(result.getUsername()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_USER_CREATION));	
	}
	
	/**************************************************************************************
	 * Read methods
	 **************************************************************************************/
	
	/**
	 * Devuelve una persona por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Persona)
	 */
	@GetMapping("id/{id}")
	@PreAuthorize("hasRole('ADMIN') or principal.id == #id")
	public ResponseEntity<Person> read(@PathVariable Long id) {
		
		Person person = personService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person", "person ID", id));
		
		return ResponseEntity.ok(person);
	}
	
	/**
	 * Devuelve todas las personas
	 * 
	 * @return Lista de personas
	 */
	@GetMapping("all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Person> readAll() {
		
		List<Person> person = StreamSupport
				.stream(personService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return person;
	}
	
}
