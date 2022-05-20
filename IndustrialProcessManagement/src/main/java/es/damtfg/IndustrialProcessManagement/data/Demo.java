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
import es.damtfg.IndustrialProcessManagement.model.company.Company;
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
 * @author Carlos Munoz
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
			
		addUsers();
		addCompanies();
		addProducts();
	}
	
	private void addUsers() {
		
		User newUser;
		Person newPerson;
		ApiResponse apiResponse;
				
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
		 * Usuarios -> Usuario
		 */
		Role userRole = roleService.findByName("ROLE_USER")
				.orElseThrow(() -> new AppException(AppMessages.EXCEPTION_USER_ROLE_NOT_SET));
		
		for(int index = 1; index <= 40; index++) {
		
			newUser = new User("user" + index, "user" + index + "@localhost.es", "user-demo", new Date());
			newPerson = new Person("Usuario " + index, "Aplicación", newUser);
			
			apiResponse = userService.create(newUser);
			
			if(apiResponse.getSuccess()) {
				
				newUser.setPerson(newPerson);
				
				newUser.setRoles(Collections.singleton(userRole));
				
				userService.save(newUser);
			}
		}
		
		
		/**
		 * Usuario -> Cliente
		 */
		newUser = new User("client", "client@localhost.es", "client-demo", new Date());
		newPerson = new Person("Cliente", "De Aplicación", newUser);
		
		apiResponse = userService.create(newUser);
		
		Role clientRole = roleService.findByName("ROLE_CLIENT")
				.orElseThrow(() -> new AppException(AppMessages.EXCEPTION_USER_ROLE_NOT_SET));
		
		if(apiResponse.getSuccess()) {
			
			newUser.setPerson(newPerson);
			
			newUser.setRoles(Collections.singleton(clientRole));
			
			userService.save(newUser);
		}
		
		/**
		 * Usuario -> Empleado
		 */
		newUser = new User("employee", "employee@localhost.es", "employee-demo", new Date());
		newPerson = new Person("Empleado", "De Aplicación", newUser);
		
		apiResponse = userService.create(newUser);
		
		Role employeeRole = roleService.findByName("ROLE_EMPLOYEE")
				.orElseThrow(() -> new AppException(AppMessages.EXCEPTION_USER_ROLE_NOT_SET));
		
		if(apiResponse.getSuccess()) {
			
			newUser.setPerson(newPerson);
			
			newUser.setRoles(Collections.singleton(employeeRole));
			
			userService.save(newUser);
		}
		
		/**
		 * Usuario -> Tester
		 */
		newUser = new User("tester", "tester@localhost.es", "tester-demo", new Date());
		newPerson = new Person("Tester", "De Aplicación", newUser);
		
		apiResponse = userService.create(newUser);
		
		Role testerRole = roleService.findByName("ROLE_TESTER")
				.orElseThrow(() -> new AppException(AppMessages.EXCEPTION_USER_ROLE_NOT_SET));
		
		if(apiResponse.getSuccess()) {
			
			newUser.setPerson(newPerson);
			
			newUser.setRoles(Collections.singleton(testerRole));
			
			userService.save(newUser);
		}
		
	}
	
	private void addCompanies() {
		
		User newUser;
		Company newCompany;
		ApiResponse apiResponse;
		
		Role companyRole = roleService.findByName("ROLE_COMPANY")
				.orElseThrow(() -> new AppException(AppMessages.EXCEPTION_USER_ROLE_NOT_SET));
		
		/**
		 * Usuarios -> Company
		 */
		for(int index = 1; index <= 20; index++) {
		
			newUser = new User("company" + index, "company" + index + "@localhost.es", "company-demo", new Date());
			newCompany = new Company("Empresa " + index, newUser);
			
			apiResponse = userService.create(newUser);
			
			if(apiResponse.getSuccess()) {
				
				newUser.setCompany(newCompany);
				
				newUser.setRoles(Collections.singleton(companyRole));
				
				userService.save(newUser);
			}
		}
		
	}
	
	private void addProducts() {
		
		ApiResponse apiResponse;
		Product newProduct;
		Recipe newRecipe;
		RecipeComponent newRecipeComponent;
		Component newComponent;
		
		/**
		 * Nuevo producto - Arcilla
		 */
		newProduct = new Product("Arcilla");
		newRecipe = new Recipe("Receta de Arcilla", newProduct);
		newComponent = new Component("Agua y barro");
		newRecipeComponent = new RecipeComponent("50kg de agua y tierra", newRecipe, newComponent);
		
		apiResponse = productService.create(newProduct);
		apiResponse = recipeService.create(newRecipe);	
		apiResponse = recipeComponentService.create(newRecipeComponent);	
		apiResponse = componentService.create(newComponent);	
		
		/**
		 * Guardamos los productos
		 */
		
		if(apiResponse.getSuccess()) {
			
			productService.save(newProduct);
			recipeService.save(newRecipe);
			recipeComponentService.save(newRecipeComponent);
			componentService.save(newComponent);
		}
		
		/**
		 * Nuevo producto - Acero
		 */
		newProduct = new Product("Acero");
		newRecipe = new Recipe("Receta de Acero", newProduct);
		newComponent = new Component("Mineral de hierro");
		newRecipeComponent = new RecipeComponent("5kg de mineral de hierro", newRecipe, newComponent);
		
		apiResponse = productService.create(newProduct);
		apiResponse = recipeService.create(newRecipe);	
		apiResponse = recipeComponentService.create(newRecipeComponent);	
		apiResponse = componentService.create(newComponent);	
		
		/**
		 * Guardamos los productos
		 */
		
		if(apiResponse.getSuccess()) {
			
			productService.save(newProduct);
			recipeService.save(newRecipe);
			recipeComponentService.save(newRecipeComponent);
			componentService.save(newComponent);
		}
		
		/**
		 * Nuevo producto - Yeso
		 */
		newProduct = new Product("Yeso");
		newRecipe = new Recipe("Receta de Yeso", newProduct);
		newComponent = new Component("Yeso, Silicio, Agua");
		newRecipeComponent = new RecipeComponent("1kg de yeso, 1kg de silicio en polvo, 1L de agua", newRecipe, newComponent);
		
		apiResponse = productService.create(newProduct);
		apiResponse = recipeService.create(newRecipe);	
		apiResponse = recipeComponentService.create(newRecipeComponent);	
		apiResponse = componentService.create(newComponent);	
		
		/**
		 * Guardamos los productos
		 */
		
		if(apiResponse.getSuccess()) {
			
			productService.save(newProduct);
			recipeService.save(newRecipe);
			recipeComponentService.save(newRecipeComponent);
			componentService.save(newComponent);
		}
		
		/**
		 * Nuevo producto - Ladrillo
		 */
		newProduct = new Product("Ladrillo");
		newRecipe = new Recipe("Receta de Ladrillo", newProduct);
		newComponent = new Component("tierra, agua y arena");
		newRecipeComponent = new RecipeComponent("1kg de tierra, 1kg arena, 1L de agua", newRecipe, newComponent);
		
		apiResponse = productService.create(newProduct);
		apiResponse = recipeService.create(newRecipe);	
		apiResponse = recipeComponentService.create(newRecipeComponent);	
		apiResponse = componentService.create(newComponent);	
		
		/**
		 * Guardamos los productos
		 */
		
		if(apiResponse.getSuccess()) {
			
			productService.save(newProduct);
			recipeService.save(newRecipe);
			recipeComponentService.save(newRecipeComponent);
			componentService.save(newComponent);
		}
		
		/**
		 * Nuevo producto - Tubos de polietileno
		 */
		newProduct = new Product("Tubos de polietileno");
		newRecipe = new Recipe("Receta de Tubos de polietileno", newProduct);
		newComponent = new Component("polietileno");
		newRecipeComponent = new RecipeComponent("1kg de polietileno", newRecipe, newComponent);
		
		apiResponse = productService.create(newProduct);
		apiResponse = recipeService.create(newRecipe);	
		apiResponse = recipeComponentService.create(newRecipeComponent);	
		apiResponse = componentService.create(newComponent);	
		
		/**
		 * Guardamos los productos
		 */
		
		if(apiResponse.getSuccess()) {
			
			productService.save(newProduct);
			recipeService.save(newRecipe);
			recipeComponentService.save(newRecipeComponent);
			componentService.save(newComponent);
		}
		
		/**
		 * Nuevo producto - Tablero de madera
		 */
		newProduct = new Product("Tablero de madera");
		newRecipe = new Recipe("Receta de Tablero de madera 122x250", newProduct);
		newComponent = new Component("Compuesto de ocume y pino");
		newRecipeComponent = new RecipeComponent("0.5kg de ocume y 0.5kg pino", newRecipe, newComponent);
		
		apiResponse = productService.create(newProduct);
		apiResponse = recipeService.create(newRecipe);	
		apiResponse = recipeComponentService.create(newRecipeComponent);	
		apiResponse = componentService.create(newComponent);	
		
		/**
		 * Guardamos los productos
		 */
		
		if(apiResponse.getSuccess()) {
			
			productService.save(newProduct);
			recipeService.save(newRecipe);
			recipeComponentService.save(newRecipeComponent);
			componentService.save(newComponent);
		}
		
	}
	
}
