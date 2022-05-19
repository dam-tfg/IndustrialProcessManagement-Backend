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
import es.damtfg.IndustrialProcessManagement.model.component.Component;
import es.damtfg.IndustrialProcessManagement.model.product.Recipe;
import es.damtfg.IndustrialProcessManagement.model.product.RecipeComponent;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.products.RecipeComponentRequest;
import es.damtfg.IndustrialProcessManagement.service.component.ComponentServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.product.RecipeComponentServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.product.RecipeServiceImpl;
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
	
	@Autowired
	private RecipeServiceImpl recipeService;
	
	@Autowired
	private ComponentServiceImpl componentService;
	
	/**
	 * Crea componente de receta.
	 * 
	 * @param recipeComponentRequest
	 * 
	 * @return ResponseEntity (HTTP Status created + Location)
	 */
	
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody RecipeComponentRequest recipeComponentRequest) {
		
		Recipe newRecipe = new Recipe(recipeComponentRequest.getName(), recipeComponentRequest.getProduct());
		
		ApiResponse apiResponse = recipeService.create(newRecipe);
		
		Component newComponent = new Component(recipeComponentRequest.getName());
		
		apiResponse = componentService.create(newComponent);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
				
		RecipeComponent newRecipeComponent = new RecipeComponent(recipeComponentRequest.getUnit(), newRecipe, newComponent);
		
		apiResponse = recipeComponentService.create(newRecipeComponent);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Recipe result = recipeService.save(newRecipe);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
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
