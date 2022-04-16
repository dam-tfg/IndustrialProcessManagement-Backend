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
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = ModelDataBase.PRODUCTION_LOG_EVENT)
public class LogEvent implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -616199446455062403L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eventId")
	private Long id;
	
	@NonNull
	@Column(name = "name", 
			length = 50)
	private String name;
	
	@NonNull
	@Column(name = "description", 
			length = 200)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = false)
	@JoinColumn(name = "eventId")
	private Set<Log> log;
	
}
