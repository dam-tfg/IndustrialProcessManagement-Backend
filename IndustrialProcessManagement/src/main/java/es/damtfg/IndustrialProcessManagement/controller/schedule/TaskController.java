package es.damtfg.IndustrialProcessManagement.controller.schedule;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.damtfg.IndustrialProcessManagement.exception.ResourceNotFoundException;
import es.damtfg.IndustrialProcessManagement.model.schedule.Task;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.schedule.TaskRequest;
import es.damtfg.IndustrialProcessManagement.service.schedule.TaskServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.TASK)
public class TaskController {
	
	@Autowired
	private TaskServiceImpl taskService;
	
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody TaskRequest taskRequest) {
				
		Task newTask = new Task(taskRequest.getDate(), taskRequest.getOrderDetails());
		
		ApiResponse apiResponse = taskService.create(newTask);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Task result = taskService.save(newTask);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getDate()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_TASK_CREATION));	
	}
	
	/**
	 * Devuelve un task por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Task)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<Task> read(@PathVariable Long id) {
		
		Task task = taskService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task", "task ID", id));
		
		return ResponseEntity.ok(task);
	}

	/**
	 * Devuelve todos los task
	 * 
	 * @return Lista de task
	 */
	@GetMapping("all")
	public List<Task> readAll() {
		
		List<Task> task = StreamSupport
				.stream(taskService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return task;
	}

}
