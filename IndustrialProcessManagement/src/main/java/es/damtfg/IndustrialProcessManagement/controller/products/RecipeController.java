package es.damtfg.IndustrialProcessManagement.controller.products;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.damtfg.IndustrialProcessManagement.model.product.Recipe;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.products.RecipeRequest;
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
	
	/**
	 * Crea receta
	 * 
	 * @param recipeRequest
	 * 
	 * @return ResponseEntity (HTTP Status created + Location)
	 */
	
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody RecipeRequest recipeRequest) {
				
		Recipe newRecipe = new Recipe(recipeRequest.getName(), recipeRequest.getProduct());
		
		ApiResponse apiResponse = recipeService.create(newRecipe);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Recipe result = recipeService.save(newRecipe);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_USER_CREATION));	
	}

}
