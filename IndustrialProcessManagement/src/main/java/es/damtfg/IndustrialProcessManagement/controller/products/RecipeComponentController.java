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
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_USER_CREATION));	
	}

}
