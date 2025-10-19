package es.upm.miw.apaw.domain.persistenceports.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Dojo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface DojoPersistence {
    Dojo create(Dojo dojo);
    BigDecimal findTotalUnitCostByCity(String city);
}
