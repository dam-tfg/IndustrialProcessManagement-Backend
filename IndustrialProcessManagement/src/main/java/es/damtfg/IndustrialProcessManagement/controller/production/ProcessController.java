package es.damtfg.IndustrialProcessManagement.controller.production;

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
import es.damtfg.IndustrialProcessManagement.model.production.Process;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.production.ProcessRequest;
import es.damtfg.IndustrialProcessManagement.service.production.ProcessServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.PROCESS)
public class ProcessController {
	
	@Autowired
	private ProcessServiceImpl processService;
	
	/**
	 * 
	 * @param processRequest
	 * @return
	 */
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody ProcessRequest processRequest) {
		
		Process newProcess = new Process(processRequest.getName(), processRequest.getSection());
		
		ApiResponse apiResponse = processService.create(newProcess);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Process result = processService.save(newProcess);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_PROCESS_CREATION));	
	}

	/**
	 * Devuelve un Process por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Process)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<Process> read(@PathVariable Long id) {
		
		Process process = processService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Process", "Process ID", id));
		
		return ResponseEntity.ok(process);
	}

	/**
	 * Devuelve todos los Process
	 * 
	 * @return Lista de Process
	 */
	@GetMapping("all")
	public List<Process> readAll() {
		
		List<Process> process = StreamSupport
				.stream(processService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return process;
	}

}
