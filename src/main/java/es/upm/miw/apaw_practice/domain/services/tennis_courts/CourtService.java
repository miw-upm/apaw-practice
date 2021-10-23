package es.upm.miw.apaw_practice.domain.services.tennis_courts;

import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.CourtPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CourtService {

    private final CourtPersistence courtPersistence;

    @Autowired
    public CourtService(CourtPersistence courtPersistence){
        this.courtPersistence = courtPersistence;
    }

    public BigDecimal get(int number){
        return this.courtPersistence.getEquipmentValues(number).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
