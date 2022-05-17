/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.model.production;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import es.damtfg.IndustrialProcessManagement.model.product.Order;
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
@Table(name = ModelDataBase.PRODUCTION_LOG)
public class Log implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -6448788496678977958L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "logId")
	private Long id;
	
	@NonNull
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", 
			updatable = false)
	private Date date; 
	
	@Column(name = "comment", 
			length = 200)
	private String comment;
	
	@ManyToOne(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId",
			nullable = true)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JoinColumn(name = "lineId",
			nullable = true)
	private Line line;
	
	@ManyToOne(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JoinColumn(name = "sectionId",
			nullable = true)
	private Section section;
	
	@ManyToOne(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JoinColumn(name = "processId",
			nullable = true)
	private Process process;
	
	@ManyToOne(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JoinColumn(name = "eventId",
			nullable = true)
	private LogEvent event;

}
