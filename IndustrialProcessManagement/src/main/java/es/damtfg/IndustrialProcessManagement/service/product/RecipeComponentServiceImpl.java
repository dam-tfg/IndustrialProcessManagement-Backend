package es.damtfg.IndustrialProcessManagement.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.product.RecipeComponent;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.product.RecipeComponentRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class RecipeComponentServiceImpl implements RecipeComponentService {
	
	@Autowired
	private RecipeComponentRepository recipeComponentRepository;

	@Override
	@Transactional
	public ApiResponse create(RecipeComponent recipeComponent) {

		recipeComponent.setUnit(recipeComponent.getUnit().trim().toLowerCase());
		
		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
	}


	@Override
	@Transactional
	public RecipeComponent save(RecipeComponent recipeComponent) {
		return recipeComponentRepository.save(recipeComponent);
	}
	
	@Override
	@Transactional
	public List<RecipeComponent> saveAll(ArrayList<RecipeComponent> recipeComponent) {
				
		int index = 0;
		
		while(index < recipeComponent.size()) {
			
			if((recipeComponent.get(index).getUnit()) != null) {
				
				recipeComponent.remove(index);
				
			} else {
				
				index++;
			}
		}
				
		return recipeComponentRepository.saveAll(recipeComponent);
	}

}
