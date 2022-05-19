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
import es.damtfg.IndustrialProcessManagement.model.product.Product;
import es.damtfg.IndustrialProcessManagement.model.product.Recipe;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.products.RecipeRequest;
import es.damtfg.IndustrialProcessManagement.service.product.ProductServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.product.RecipeServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.RECIPE)
public class RecipeController {
	
	@Autowired
	private RecipeServiceImpl recipeService;
	
	@Autowired
	private ProductServiceImpl productService;
	
	/**
	 * Crea receta
	 * 
	 * @param recipeRequest
	 * 
	 * @return ResponseEntity (HTTP Status created + Location)
	 */
	
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody RecipeRequest recipeRequest) {
		
		Product newProduct = new Product(recipeRequest.getName());
		
		ApiResponse apiResponse = productService.create(newProduct);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
				
		Recipe newRecipe = new Recipe(recipeRequest.getName(), recipeRequest.getProduct());
		
		newProduct.setRecipe(newRecipe);
		
		apiResponse = recipeService.create(newRecipe);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Product result = productService.save(newProduct);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_RECIPE_CREATION));	
	}
	
	/**
	 * Devuelve un recipe por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Recipe)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<Recipe> read(@PathVariable Long id) {
		
		Recipe recipe = recipeService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe", "recipe ID", id));
		
		return ResponseEntity.ok(recipe);
	}

	/**
	 * Devuelve todos los Recipe
	 * 
	 * @return Lista de Recipe
	 */
	@GetMapping("all")
	public List<Recipe> readAll() {
		
		List<Recipe> recipe = StreamSupport
				.stream(recipeService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return recipe;
	}

}
