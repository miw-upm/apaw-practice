package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import java.math.BigDecimal;
import java.util.stream.Stream;

@Repository
public interface HospitalPersistence {
    Stream<Hospital> readAll();


}