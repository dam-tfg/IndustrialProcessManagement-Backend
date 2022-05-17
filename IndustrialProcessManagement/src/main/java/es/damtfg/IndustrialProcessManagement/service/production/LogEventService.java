package es.damtfg.IndustrialProcessManagement.service.production;

import es.damtfg.IndustrialProcessManagement.model.production.LogEvent;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface LogEventService {
	
	/**
	 * Crea un nuevo componente sin persistencia
	 * 
	 * @param componente
	 * @return
	 */
	public ApiResponse create(LogEvent logEvent);
	
	/**
	 * Comprueba la existencia de un componente
	 * 
	 * @param name
	 * 
	 * @return
	 */
	public Boolean existsByName(String name);
	
	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param user
	 * 
	 * @return Objeto componente guardado
	 */
	public LogEvent save(LogEvent logEvent);

}
