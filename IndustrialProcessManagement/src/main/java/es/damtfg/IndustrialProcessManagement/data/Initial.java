/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.data;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import es.damtfg.IndustrialProcessManagement.model.production.LogEvent;
import es.damtfg.IndustrialProcessManagement.model.user.security.Role;
import es.damtfg.IndustrialProcessManagement.service.user.security.RoleServiceImpl;

/**
 * @author Alberto González
 *
 */
public class Initial implements ApplicationRunner {

	@Autowired
	private RoleServiceImpl roleService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		/**
		 * ROLES
		 */
		ArrayList<Role> role = new ArrayList<Role>();
		
		role.add(new Role("ROLE_ADMIN", "Administrador"));
		role.add(new Role("ROLE_USER", "Usuario de app"));
		role.add(new Role("ROLE_EMPLOYEE", "Trabajador de la empresa"));
		role.add(new Role("ROLE_TESTER", "Testeador de la app"));
		
//		for(int index = 0; index < role.size(); index++) {
//						
//			if(!roleService.findByName("ROLE_" + role.get(index).trim().toUpperCase()).isPresent()) {
//				
//				roleService.save(new Role("ROLE_" + role.get(index).trim().toUpperCase(), ""));
//			}
//		}
		
		roleService.saveAll(role);
		
		
		
		/**
		 * EVENTS
		 */
		ArrayList<LogEvent> event = new ArrayList<LogEvent>();
				
		event.add(new LogEvent("START", "Comienzo de la fabricación"));
		event.add(new LogEvent("PAUSE", "Parada momentánea de la fabricación"));
		event.add(new LogEvent("FORCE_PAUSE", "Parada momentánea forzada de la fabricación"));
		event.add(new LogEvent("STOP", "Detención de la fabricación"));

		// TODO
		System.out.println("--> TODO - Implementar app data load");
	}
	
}