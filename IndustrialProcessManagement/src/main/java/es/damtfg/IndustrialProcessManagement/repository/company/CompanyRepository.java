package es.damtfg.IndustrialProcessManagement.repository.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.company.Company;
import es.damtfg.IndustrialProcessManagement.model.component.Stock;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository <Company, Long> {
	
	Boolean findByName(String name);

	Stock save(Stock stock);

}
