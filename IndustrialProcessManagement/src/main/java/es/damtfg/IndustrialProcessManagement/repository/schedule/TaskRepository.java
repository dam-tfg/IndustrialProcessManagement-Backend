package es.damtfg.IndustrialProcessManagement.repository.schedule;

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

	Task save(Task task);

}
