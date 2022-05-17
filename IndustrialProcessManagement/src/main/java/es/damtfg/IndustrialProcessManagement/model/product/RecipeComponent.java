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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import es.damtfg.IndustrialProcessManagement.model.component.Component;
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
@Table(name = ModelDataBase.PRODUCT_RECIPE_COMPONENTS)
public class RecipeComponent implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 9116999897839938939L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipeComponentId")
	private Long id;
	
	@Min(value = 0)
	@Column(name = "quantity")
	private float quantity;
	
	@NotBlank
	@NonNull
	@Size(max = 50)
	@Column(name = "unit")
	private String unit;
	
	@NonNull
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "recipeId",
			updatable = false,
			nullable = false)
	@JsonBackReference
	private Recipe recipe;
	
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JoinColumn(name = "componentId")
	private Component component;
	
}
