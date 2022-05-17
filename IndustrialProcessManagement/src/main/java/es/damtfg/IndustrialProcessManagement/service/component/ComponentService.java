package es.damtfg.IndustrialProcessManagement.service.component;

import es.damtfg.IndustrialProcessManagement.model.component.Component;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface ComponentService {
	
	/**
	 * Crea un nuevo componente sin persistencia
	 * 
	 * @param componente
	 * @return
	 */
	public ApiResponse create(Component component);
	
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
	public Component save(Component component);

}
