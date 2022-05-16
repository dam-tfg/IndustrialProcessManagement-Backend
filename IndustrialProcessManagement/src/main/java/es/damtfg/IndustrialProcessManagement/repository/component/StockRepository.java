package es.damtfg.IndustrialProcessManagement.repository.component;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.component.Stock;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface StockRepository extends JpaRepository <Stock, Long> {

	Stock save(Stock stock);

}
