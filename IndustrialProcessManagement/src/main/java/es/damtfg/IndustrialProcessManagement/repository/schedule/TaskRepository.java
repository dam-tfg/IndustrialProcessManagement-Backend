package es.damtfg.IndustrialProcessManagement.repository.schedule;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.schedule.Task;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {

	/**
	 * Búsqueda por ID.
	 * 
	 * @param id
	 * @return Optional
	 */
	Optional<Task> findById(String id);

}
