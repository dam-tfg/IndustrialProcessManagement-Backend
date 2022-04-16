/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.model.product;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.damtfg.IndustrialProcessManagement.util.constants.ModelDataBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Alberto González
 *
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = ModelDataBase.PRODUCT_RECIPE)
public class Recipe implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 190633001809172036L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipeId")
	private Long id;
	
	@NonNull
	@Column(name = "name", 
			length = 50)
	private String name;
	
	@Column(name = "description", 
			length = 200)
	private String description;
	
	@NonNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId",
			nullable = false,
			unique = true)
	@JsonBackReference
	private Product product;

	@OneToOne(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "recipe")
	@JsonManagedReference
	private RecipeComponent recipeComponent;
	
}
