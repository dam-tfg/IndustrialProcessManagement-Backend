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
import es.damtfg.IndustrialProcessManagement.model.production.Line;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.production.LineRequest;
import es.damtfg.IndustrialProcessManagement.service.production.LineServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.LINE)
public class LineController {
	
	@Autowired
	private LineServiceImpl lineService;
	
	/**
	 * @param lineRequest
	 * 
	 * @return ResponseEntity (HTTP Status created + Location)
	 */
	
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody LineRequest lineRequest) {
		
		Line newLine = new Line(lineRequest.getName());
		
		ApiResponse apiResponse = lineService.create(newLine);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Line result = lineService.save(newLine);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_LINE_CREATION));	
	}

	/**
	 * Devuelve un line por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Line)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<Line> read(@PathVariable Long id) {
		
		Line line = lineService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Line", "line ID", id));
		
		return ResponseEntity.ok(line);
	}

	/**
	 * Devuelve todos los line
	 * 
	 * @return Lista de line
	 */
	@GetMapping("all")
	public List<Line> readAll() {
		
		List<Line> line = StreamSupport
				.stream(lineService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return line;
	}
	
}
