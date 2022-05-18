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
import es.damtfg.IndustrialProcessManagement.model.production.Log;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.service.production.LogServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.LOG)
public class LogController {

	@Autowired
	private LogServiceImpl logService;

	/**
	 * @param logRequest
	 * 
	 * @return ResponseEntity (HTTP Status created + Location)
	 */

	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody Log logRequest) {

		Log newLog = new Log(logRequest.getDate());

		ApiResponse apiResponse = logService.create(newLog);

		if (!apiResponse.getSuccess())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);

		Log result = logService.save(newLog);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getDate()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_LOG_CREATION));

	}
	
	/**
	 * Devuelve un log por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Log)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<Log> read(@PathVariable Long id) {
		
		Log log = logService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Log", "log ID", id));
		
		return ResponseEntity.ok(log);
	}

	/**
	 * Devuelve todos los log
	 * 
	 * @return Lista de log
	 */
	@GetMapping("all")
	public List<Log> readAll() {
		
		List<Log> log = StreamSupport
				.stream(logService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return log;
	}

}
