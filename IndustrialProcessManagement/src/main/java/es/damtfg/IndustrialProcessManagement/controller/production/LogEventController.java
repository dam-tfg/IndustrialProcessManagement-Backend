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
import es.damtfg.IndustrialProcessManagement.model.production.LogEvent;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.service.production.LogEventServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.LOG_EVENT)
public class LogEventController {
	
	@Autowired
	private LogEventServiceImpl logEventService;
	
	/**
	 * 
	 * @param logEventRequest
	 * @return
	 */

	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody LogEvent logEventRequest) {
		
		LogEvent newLogEvent = new LogEvent(logEventRequest.getName(), logEventRequest.getDescription());
		
		ApiResponse apiResponse = logEventService.create(newLogEvent);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		LogEvent result = logEventService.save(newLogEvent);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_LOGEVENT_CREATION));	
	}
	
	/**
	 * Devuelve un logEvent por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + logEvent)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<LogEvent> read(@PathVariable Long id) {
		
		LogEvent logEvent = logEventService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LogEvent", "logEvent ID", id));
		
		return ResponseEntity.ok(logEvent);
	}

	/**
	 * Devuelve todos los logEvent
	 * 
	 * @return Lista de logEvent
	 */
	@GetMapping("all")
	public List<LogEvent> readAll() {
		
		List<LogEvent> logEvent = StreamSupport
				.stream(logEventService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return logEvent;
	}

}
