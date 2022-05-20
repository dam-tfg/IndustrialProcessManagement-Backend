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
import es.damtfg.IndustrialProcessManagement.service.production.LogEventServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.user.security.RoleServiceImpl;

/**
 * @author Alberto González
 *
 */
public class Initial implements ApplicationRunner {

	@Autowired
	private RoleServiceImpl roleService;
	
	@Autowired
	private LogEventServiceImpl eventService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		addRoles();
		addEvents();
	}
	
	/**
	 * Roles
	 */
	private void addRoles() {
		
		ArrayList<Role> role = new ArrayList<Role>();
		
		role.add(new Role("ROLE_SUPER", "Super usuario"));
		role.add(new Role("ROLE_ADMIN", "Administrador"));
		role.add(new Role("ROLE_USER", "Usuario de app"));
		role.add(new Role("ROLE_CLIENT", "Client genérico"));
		role.add(new Role("ROLE_COMPANY", "Empresa externa"));
		role.add(new Role("ROLE_EMPLOYEE", "Trabajador de la empresa"));
		role.add(new Role("ROLE_TESTER", "Testeador de la app"));
				
		roleService.saveAll(role);
	}
	
	/**
	 * Events
	 */
	private void addEvents() {
		
		ArrayList<LogEvent> event = new ArrayList<LogEvent>();
		
		event.add(new LogEvent("START", "Comienzo de la fabricación"));
		event.add(new LogEvent("PAUSE", "Parada momentánea de la fabricación"));
		event.add(new LogEvent("FORCE_PAUSE", "Parada momentánea forzada de la fabricación"));
		event.add(new LogEvent("STOP", "Detención de la fabricación"));
		
		eventService.saveAll(event);
	}
}