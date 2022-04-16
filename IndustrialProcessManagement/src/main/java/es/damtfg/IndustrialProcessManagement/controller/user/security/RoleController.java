/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.controller.user.security;

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

import es.damtfg.IndustrialProcessManagement.exception.ResourceNotFoundException;
import es.damtfg.IndustrialProcessManagement.model.user.security.Role;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.service.user.security.RoleServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * @author Alberto González
 *
 */
@RestController
@RequestMapping(ApiPath.ROLE)
public class RoleController {
	
	@Autowired
	private RoleServiceImpl roleService;
	
	/**************************************************************************************
	 * Create methods
	 **************************************************************************************/

	/**
	 * Crea un rol de acceso.
	 * 
	 * @param role
	 * 
	 * @return ResponseEntity (HTTP Status created)
	 */
	@PostMapping("new")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody Role role) {
		
		role.setName(role.getName().trim().toUpperCase());
		
		if(!role.getName().startsWith("ROLE_")) role.setName("ROLE_" + role.getName());
		
		if(roleService.findByName(role.getName()).isPresent()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, AppMessages.ERROR_ROLE_EXIST));
		}
		
		roleService.save(role);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, AppMessages.SUCCESS_ROLE_CREATION));
	}
	
	/**************************************************************************************
	 * Read methods
	 **************************************************************************************/
	
	/**
	 * Devuelve un rol por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Rol)
	 */
	@GetMapping("id/{id}")
	@PreAuthorize("hasRole('ADMIN') or principal.id == #id")
	public ResponseEntity<Role> read(@PathVariable Long id) {
		
		Role role = roleService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "role ID", id));
		
		return ResponseEntity.ok(role);
	}
	
}
