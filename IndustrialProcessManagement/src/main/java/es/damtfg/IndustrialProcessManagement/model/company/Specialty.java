/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.model.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@Table(name = ModelDataBase.COMPANY_EMPLOYEE_SPECIALTY)
public class Specialty implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 7866494913587960125L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "specialtyId")
	private Long id;
	
	@NotBlank
	@NonNull
	@Size(max = 20)
	@Column(name = "name")
	private String name;
	
	@NotBlank
	@NonNull
	@Size(max = 250)
	@Column(name = "description")
	private String description;
	
}
