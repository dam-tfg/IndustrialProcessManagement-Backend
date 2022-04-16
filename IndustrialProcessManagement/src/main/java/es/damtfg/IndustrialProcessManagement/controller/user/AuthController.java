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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.damtfg.IndustrialProcessManagement.payload.security.JwtAuthenticationResponse;
import es.damtfg.IndustrialProcessManagement.payload.user.LoginRequest;
import es.damtfg.IndustrialProcessManagement.security.JwtTokenProvider;
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
		
}
