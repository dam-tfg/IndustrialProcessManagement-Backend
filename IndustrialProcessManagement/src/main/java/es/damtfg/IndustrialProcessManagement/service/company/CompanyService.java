package es.damtfg.IndustrialProcessManagement.service.company;

import es.damtfg.IndustrialProcessManagement.model.company.Company;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface CompanyService {

	public Boolean existsByName(String name);
	
	public Company save(Company company);


}
