package es.damtfg.IndustrialProcessManagement.service.production;

import es.damtfg.IndustrialProcessManagement.model.production.Line;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface LineService {
	
	/**
	 * Crea un nuevo componente sin persistencia
	 * 
	 * @param componente
	 * @return
	 */
	public ApiResponse create(Line line);
	
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
	public Line save(Line line);

}
