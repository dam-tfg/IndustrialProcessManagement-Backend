package es.damtfg.IndustrialProcessManagement.service.schedule;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.schedule.Task;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.schedule.TaskRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	@Transactional
	public ApiResponse create(Task task) {

		task.setDate(task.getDate());

		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
		
	}

	@Override
	@Transactional
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Transactional(readOnly = true)
	public Optional<Task> findById(Long id) {
		return taskRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

}
