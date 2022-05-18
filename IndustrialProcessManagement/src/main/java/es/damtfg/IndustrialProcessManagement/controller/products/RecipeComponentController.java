package es.damtfg.IndustrialProcessManagement.controller.products;

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
import es.damtfg.IndustrialProcessManagement.model.product.RecipeComponent;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.products.RecipeComponentRequest;
import es.damtfg.IndustrialProcessManagement.service.product.RecipeComponentServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.RECIPE_COMPONENT)
public class RecipeComponentController {
	
	@Autowired
	private RecipeComponentServiceImpl recipeComponentService;
	
	/**
	 * Crea componente de receta.
	 * 
	 * @param recipeComponentRequest
	 * 
	 * @return ResponseEntity (HTTP Status created + Location)
	 */
	
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody RecipeComponentRequest recipeComponentRequest) {
				
		RecipeComponent newRecipeComponent = new RecipeComponent(recipeComponentRequest.getUnit(), recipeComponentRequest.getRecipe(), recipeComponentRequest.getComponent());
		
		ApiResponse apiResponse = recipeComponentService.create(newRecipeComponent);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		RecipeComponent result = recipeComponentService.save(newRecipeComponent);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getUnit()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_RECIPECOMPONENT_CREATION));	
	}
	
	/**
	 * Devuelve un RecipeComponent por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + RecipeComponent)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<RecipeComponent> read(@PathVariable Long id) {
		
		RecipeComponent recipeComponent = recipeComponentService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("RecipeComponent", "RecipeComponent ID", id));
		
		return ResponseEntity.ok(recipeComponent);
	}

	/**
	 * Devuelve todos los RecipeComponent
	 * 
	 * @return Lista de RecipeComponent
	 */
	@GetMapping("all")
	public List<RecipeComponent> readAll() {
		
		List<RecipeComponent> recipeComponent = StreamSupport
				.stream(recipeComponentService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return recipeComponent;
	}

}
