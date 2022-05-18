/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.data;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import es.damtfg.IndustrialProcessManagement.exception.AppException;
import es.damtfg.IndustrialProcessManagement.model.component.Component;
import es.damtfg.IndustrialProcessManagement.model.product.Product;
import es.damtfg.IndustrialProcessManagement.model.product.Recipe;
import es.damtfg.IndustrialProcessManagement.model.product.RecipeComponent;
import es.damtfg.IndustrialProcessManagement.model.user.Person;
import es.damtfg.IndustrialProcessManagement.model.user.User;
import es.damtfg.IndustrialProcessManagement.model.user.security.Role;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.service.component.ComponentServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.product.ProductServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.product.RecipeComponentServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.product.RecipeServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.user.UserServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.user.security.RoleServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * @author Alberto González
 *
 */
/**
 * 
 * @author  Carlos Munoz
 *
 */
public class Demo implements CommandLineRunner {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private RecipeServiceImpl recipeService;
	
	@Autowired
	private RecipeComponentServiceImpl recipeComponentService;
	
	@Autowired
	private ComponentServiceImpl componentService;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
			
		User newUser;
		Person newPerson;
		ApiResponse apiResponse;
		Product newProduct;
		Recipe newRecipe;
		RecipeComponent newRecipeComponent;
		Component newComponent;
		
		Role userRole = roleService.findByName("ROLE_ADMIN")
				.orElseThrow(() -> new AppException(AppMessages.EXCEPTION_USER_ROLE_NOT_SET));
		
		/**
		 * Usuario -> Administrador
		 */
		newUser = new User("administrator", "administrator@localhost.es", "administrator-demo", new Date());
		newPerson = new Person("Administrador", "De Administradores", newUser);
		
		apiResponse = userService.create(newUser);
		
		if(apiResponse.getSuccess()) {
			
			newUser.setPerson(newPerson);
			
			newUser.setRoles(new HashSet<Role>(roleService.listAll()));
			
			userService.save(newUser);
		}
		
		/**
		 * Usuario -> Usuario
		 */
		newUser = new User("user", "user@localhost.es", "user-demo", new Date());
		newPerson = new Person("Usuario", "De Aplicación", newUser);
		
		apiResponse = userService.create(newUser);
		
		if(apiResponse.getSuccess()) {
			
			newUser.setPerson(newPerson);
			
			newUser.setRoles(Collections.singleton(userRole));
			
			userService.save(newUser);
		}
		
		/**
		 * Nuevo producto
		 */
		newProduct = new Product("Arcilla");
		newRecipe = new Recipe("Receta de Arcilla", newProduct);
		newComponent = new Component("Agua y barro");
		newRecipeComponent = new RecipeComponent("50kg de agua y tierra", newRecipe, newComponent);
		
		apiResponse = productService.create(newProduct);
		apiResponse = recipeService.create(newRecipe);	
		apiResponse = recipeComponentService.create(newRecipeComponent);	
		apiResponse = componentService.create(newComponent);	
		
		// Guardamos productos
		
		if(apiResponse.getSuccess()) {
			
			productService.save(newProduct);
			recipeService.save(newRecipe);
			recipeComponentService.save(newRecipeComponent);
			componentService.save(newComponent);
		}
	}
	
}
