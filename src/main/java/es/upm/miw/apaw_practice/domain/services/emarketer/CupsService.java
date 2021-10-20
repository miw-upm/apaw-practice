package es.upm.miw.apaw_practice.domain.services.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Cups;
import es.upm.miw.apaw_practice.domain.persistence_ports.emarketer.CupsPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupsService {

    private final CupsPersistence cupsPersistence;

    @Autowired
    public CupsService(CupsPersistence cupsPersistence) {
        this.cupsPersistence = cupsPersistence;
    }

    public void updateEnergy(String cup, Cups cups) {
        Cups cupsResult = this.cupsPersistence.readByCup(cup);
        cupsResult.setEnergy(cups.getEnergy());
        this.cupsPersistence.update(cupsResult);
    }
}
