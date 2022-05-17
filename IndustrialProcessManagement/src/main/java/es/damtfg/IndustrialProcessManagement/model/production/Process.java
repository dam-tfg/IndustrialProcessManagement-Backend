/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.model.production;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

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
@Table(name = ModelDataBase.PRODUCTION_PROCESS)
public class Process implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1887350793013825756L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "processId")
	private Long id;
	
	@NonNull
	@Column(name = "name", 
			length = 50)
	private String name;
	
	@Column(name = "description", 
			length = 200)
	private String description;
	
	@Min(value = 0)
	@Column(name = "status")
	private int status;
	
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JoinColumn(name = "sectionId")
	private Section section;
	
	@OneToMany(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = false)
	@JoinColumn(name = "processId")
	private Set<Log> log;
	
}
