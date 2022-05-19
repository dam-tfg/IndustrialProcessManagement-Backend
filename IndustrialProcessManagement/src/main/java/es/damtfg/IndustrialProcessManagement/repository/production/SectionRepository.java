package es.damtfg.IndustrialProcessManagement.repository.production;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.production.Line;
import es.damtfg.IndustrialProcessManagement.model.production.Section;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface SectionRepository extends JpaRepository <Section, Long> {
	
	/**
	 * Busca por nombre de producto.
	 * 
	 * @param name
	 * 
	 * @return Optional<producto>
	 */
	Boolean findByName(String name);

	/**
	 * Búsqueda por ID.
	 * 
	 * @param id
	 * @return Optional
	 */
	Optional<Section> findById(String id);

	Line save(Line line);

}
