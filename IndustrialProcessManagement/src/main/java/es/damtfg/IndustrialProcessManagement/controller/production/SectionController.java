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
import es.damtfg.IndustrialProcessManagement.model.production.Section;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.production.SectionRequest;
import es.damtfg.IndustrialProcessManagement.service.production.SectionServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.SECTION)
public class SectionController {
	
	@Autowired
	private SectionServiceImpl sectionService;
	
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody SectionRequest sectionRequest) {
		
		Section newSection = new Section(sectionRequest.getName(), sectionRequest.getLine());
		
		ApiResponse apiResponse = sectionService.create(newSection);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Section result = sectionService.save(newSection);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_SECTION_CREATION));	
	}

	/**
	 * Devuelve un Section por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Section)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<Section> read(@PathVariable Long id) {
		
		Section section = sectionService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Section", "Section ID", id));
		
		return ResponseEntity.ok(section);
	}

	/**
	 * Devuelve todos los Section
	 * 
	 * @return Lista de Section
	 */
	@GetMapping("all")
	public List<Section> readAll() {
		
		List<Section> section = StreamSupport
				.stream(sectionService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return section;
	}
	
}
