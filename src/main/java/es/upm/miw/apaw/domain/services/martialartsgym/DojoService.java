package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Dojo;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.DojoPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
@Service
public class DojoService {

    private final DojoPersistence dojoPersistence;

    @Autowired
    public DojoService(DojoPersistence dojoPersistence) {
        this.dojoPersistence = dojoPersistence;
    }

    public Dojo create(Dojo dojo) {
        return this.dojoPersistence.create(dojo);
    }
    public BigDecimal findTotalUnitCostByCity(String city) {
        return this.dojoPersistence.findTotalUnitCostByCity(city);
    }
}
