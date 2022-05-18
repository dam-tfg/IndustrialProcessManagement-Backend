package es.damtfg.IndustrialProcessManagement.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.product.Recipe;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.product.RecipeRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	/**
	 * Creación de una nueva receta
	 * 
	 * @param Product
	 * 
	 * @return ApiResponse
	 */
	@Override
	@Transactional
	public ApiResponse create(Recipe recipe) {

		recipe.setName(recipe.getName().trim().toLowerCase());

		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
	}

	@Override
	@Transactional
	public Boolean existsByName(String name) {
		return recipeRepository.findByName(name);
	}

	@Override
	@Transactional
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Transactional(readOnly = true)
	public Optional<Recipe> findById(Long id) {
		return recipeRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}


}
