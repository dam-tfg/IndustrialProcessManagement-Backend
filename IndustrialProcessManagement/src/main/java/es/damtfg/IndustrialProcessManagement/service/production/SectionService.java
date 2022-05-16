package es.damtfg.IndustrialProcessManagement.service.production;

import es.damtfg.IndustrialProcessManagement.model.production.Section;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface SectionService {
	
	/**
	 * Crea un nuevo componente sin persistencia
	 * 
	 * @param componente
	 * @return
	 */
	public ApiResponse create(Section section);
	
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
	public Section save(Section section);

}
