package es.damtfg.IndustrialProcessManagement.repository.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.company.Specialty;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface SpecialtyRepository extends JpaRepository <Specialty, Long> {
	
	Boolean findByName(String name);

	@SuppressWarnings("unchecked")
	Specialty save(Specialty specialty);

}
