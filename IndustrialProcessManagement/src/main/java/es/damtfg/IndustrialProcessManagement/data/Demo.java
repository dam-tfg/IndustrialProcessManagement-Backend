/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.data;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import es.damtfg.IndustrialProcessManagement.exception.AppException;
import es.damtfg.IndustrialProcessManagement.model.user.Person;
import es.damtfg.IndustrialProcessManagement.model.user.User;
import es.damtfg.IndustrialProcessManagement.model.user.security.Role;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.service.user.UserServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.user.security.RoleServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * @author Alberto González
 *
 */
public class Demo implements CommandLineRunner {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
			
		User newUser;
		Person newPerson;
		ApiResponse apiResponse;
		
		Role userRole = roleService.findByName("ROLE_ADMIN")
				.orElseThrow(() -> new AppException(AppMessages.EXCEPTION_USER_ROLE_NOT_SET));
		
		/**
		 * Usuario -> Administrador
		 */
		newUser = new User("administrator", "administrator@localhost.es", "administrator-demo", new Date());
		newPerson = new Person("Administrador", "De Administradores", newUser);
		
		apiResponse = userService.create(newUser);
		
		if(apiResponse.getSuccess()) {
			
			newUser.setPerson(newPerson);
			
			newUser.setRoles(new HashSet<Role>(roleService.listAll()));
			
			userService.save(newUser);
		}
		
		/**
		 * Usuario -> Usuario
		 */
		newUser = new User("user", "user@localhost.es", "user-demo", new Date());
		newPerson = new Person("Usuario", "De Aplicación", newUser);
		
		apiResponse = userService.create(newUser);
		
		if(apiResponse.getSuccess()) {
			
			newUser.setPerson(newPerson);
			
			newUser.setRoles(Collections.singleton(userRole));
			
			userService.save(newUser);
		}
				
	}
	
}
