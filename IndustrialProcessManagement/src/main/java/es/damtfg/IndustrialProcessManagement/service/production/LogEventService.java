package es.damtfg.IndustrialProcessManagement.service.production;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.damtfg.IndustrialProcessManagement.model.production.LogEvent;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * @author Alberto González
 * @author Carlos Munoz
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
	 * Busca por nombre de producto.
	 * 
	 * @param name
	 * 
	 * @return Optional<LogEvent>
	 */
	public Optional<LogEvent> findByName(String name);

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
	
	/**
	 * Crea la persistencia de los objetos.
	 * 
	 * @param ArrayList<LogEvent>
	 * 
	 * @return Lista de objetos guardados
	 */
	public List<LogEvent> saveAll(ArrayList<LogEvent> event);

}
