/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.damtfg.IndustrialProcessManagement.exception.AppException;
import es.damtfg.IndustrialProcessManagement.model.user.security.Role;
import es.damtfg.IndustrialProcessManagement.payload.security.JwtAuthenticationResponse;
import es.damtfg.IndustrialProcessManagement.payload.user.LoginRequest;
import es.damtfg.IndustrialProcessManagement.repository.user.security.UserPrincipal;
import es.damtfg.IndustrialProcessManagement.security.CurrentUser;
import es.damtfg.IndustrialProcessManagement.security.JwtTokenProvider;
import es.damtfg.IndustrialProcessManagement.service.user.security.RoleServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * @author Alberto González
 *
 */
@RestController
@RequestMapping(ApiPath.AUTH)
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider; 
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@PostMapping("login")
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
					loginRequest.getUsernameOrEmail(), 
					loginRequest.getPassword()
			)
			
		);    
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = tokenProvider.generateToken(authentication);
						
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
	
	@GetMapping("check-authorization/{roleName}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Boolean> isAuthorized(@CurrentUser UserPrincipal currentUser, @Valid @PathVariable String roleName) {
		
		roleName = roleName.toUpperCase();
		
		roleName = (roleName.contains("ROLE_")) ? roleName : "ROLE_" + roleName;
		
		Role role = roleService.findByName(roleName)
				.orElseThrow(() -> new AppException(AppMessages.EXCEPTION_USER_ROLE_NOT_SET));
		
		if(currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SUPER"))) return ResponseEntity.ok(true);
		
		return ResponseEntity.ok(currentUser.getAuthorities().contains(new SimpleGrantedAuthority(role.getName())));
	}
		
}
