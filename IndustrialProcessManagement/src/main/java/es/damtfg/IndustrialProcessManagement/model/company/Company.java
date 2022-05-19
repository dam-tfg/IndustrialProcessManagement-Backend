/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.model.company;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.damtfg.IndustrialProcessManagement.model.product.Order;
import es.damtfg.IndustrialProcessManagement.model.user.Person;
import es.damtfg.IndustrialProcessManagement.model.user.User;
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
@Table(name = ModelDataBase.COMPANY, 
	uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"companyId"
		})
})
public class Company implements Serializable {
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -1435457570620798097L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "companyId")
	private Long id;
	
	@NotBlank
	@NonNull
	@Size(max = 60)
	@Column(name = "name")
	private String name;
	
	@NonNull
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId",
			updatable = false,
			nullable = false,
			unique = true)
	@JsonManagedReference
	private User user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personId",
			updatable = true)
	@JsonBackReference
	private Person person;
	
	@OneToMany(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = false)
	@JoinColumn(name = "companyId")
	@JsonBackReference
	private Set<Order> order;

}
