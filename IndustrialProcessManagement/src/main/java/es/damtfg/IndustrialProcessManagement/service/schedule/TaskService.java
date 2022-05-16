package es.damtfg.IndustrialProcessManagement.service.schedule;

import es.damtfg.IndustrialProcessManagement.model.schedule.Task;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface TaskService {
	
	/**
	 * Crea un nuevo componente sin persistencia
	 * 
	 * @param componente
	 * @return
	 */
	public ApiResponse create(Task task);
	
	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param user
	 * 
	 * @return Objeto componente guardado
	 */
	public Task save(Task task);
	
	

}
