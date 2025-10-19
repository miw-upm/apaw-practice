package es.upm.miw.apaw.domain.persistenceports.warehouse;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OrderDetailPersistence {

    BigDecimal findBySumUnitCostDistinctByPosition(String position);

}
