package es.damtfg.IndustrialProcessManagement.controller.company;

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
import es.damtfg.IndustrialProcessManagement.model.company.Specialty;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.service.company.SpecialtyServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.SPECIALTY)
public class SpecialtyController {

	@Autowired
	private SpecialtyServiceImpl specialtyService;
	
	/**
	 * 
	 * @param specialtyRequest
	 * @return
	 */
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody Specialty specialtyRequest) {
		
		Specialty newSpecialty = new Specialty(specialtyRequest.getName(), specialtyRequest.getDescription());
		
		ApiResponse apiResponse = specialtyService.create(newSpecialty);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Specialty result = specialtyService.save(newSpecialty);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_SPECIALTY_CREATION));	
	}

	/**
	 * Devuelve dato specialty por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + specialty)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<Specialty> read(@PathVariable Long id) {
		
		Specialty specialty = specialtyService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Specialty", "specialty ID", id));
		
		return ResponseEntity.ok(specialty);
	}

	/**
	 * Devuelve todos los datos specialty
	 * 
	 * @return Lista de specialty
	 */
	@GetMapping("all")
	public List<Specialty> readAll() {
		
		List<Specialty> specialty = StreamSupport
				.stream(specialtyService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return specialty;
	}
	
}
