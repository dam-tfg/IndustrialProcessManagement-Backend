package es.damtfg.IndustrialProcessManagement.service.company;

import es.damtfg.IndustrialProcessManagement.model.company.Specialty;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface SpecialtyService {

	public ApiResponse create(Specialty speciality);
	
	public Boolean existsByName(String name);
	
	public Specialty save(Specialty speciality);

}
