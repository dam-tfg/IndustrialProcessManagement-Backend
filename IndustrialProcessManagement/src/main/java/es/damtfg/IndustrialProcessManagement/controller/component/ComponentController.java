package es.damtfg.IndustrialProcessManagement.controller.component;

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
import es.damtfg.IndustrialProcessManagement.model.component.Component;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.service.component.ComponentServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.COMPONENT)
public class ComponentController {
	
	@Autowired
	private ComponentServiceImpl componentService;
	
	/**
	 * 
	 * @param componentRequest
	 * @return
	 */
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody Component componentRequest) {
		
		Component newComponent = new Component(componentRequest.getName());
		
		ApiResponse apiResponse = componentService.create(newComponent);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Component result = componentService.save(newComponent);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_COMPONENT_CREATION));	
	}
	
	/**
	 * Devuelve un componentes por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + componentes)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<Component> read(@PathVariable Long id) {
		
		Component component = componentService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Component", "component ID", id));
		
		return ResponseEntity.ok(component);
	}

	/**
	 * Devuelve todos los componentes
	 * 
	 * @return Lista de componentes
	 */
	@GetMapping("all")
	public List<Component> readAll() {
		
		List<Component> component = StreamSupport
				.stream(componentService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return component;
	}

}
