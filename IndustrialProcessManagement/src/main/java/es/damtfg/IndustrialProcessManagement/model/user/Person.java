/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.model.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.damtfg.IndustrialProcessManagement.model.company.Company;
import es.damtfg.IndustrialProcessManagement.model.company.Specialty;
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
@Table(name = ModelDataBase.USERS_PERSON)
public class Person implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -6827481469930818500L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personId")
	private Long id;
	
	@NotBlank
	@NonNull
	@Size(max = 20)
	@Column(name = "name")
	private String name;
	
	@NotBlank
	@NonNull
	@Size(max = 40)
	@Column(name = "surname")
	private String surname;
		
	@NonNull
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId",
			updatable = false,
			nullable = false,
			unique = true)
	@JsonManagedReference
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "person")
	@JsonBackReference
	private Company company;

	@NonNull
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	@JoinTable(name = ModelDataBase.COMPANY_EMPLOYEE,
			joinColumns = @JoinColumn(name = "personId"),
			inverseJoinColumns = @JoinColumn(name = "specialtyId"))
	@JsonBackReference
	private Set<Specialty> especialty = new HashSet<>();
	
}
