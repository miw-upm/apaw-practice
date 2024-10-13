package es.upm.miw.apaw_practice.domain.services.Hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException
import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.HospitalPersistence;

@Service
public class HospitalService {
    private final HospitalPersistence hospitalPersistence;

    @Autowired
    public HospitalService(HospitalPersistence hospitalPersistence) {
        this.hospitalPersistence = hospitalPersistence;
    }
    public Stream<Hospital> readAll() {
        return this.hospitalPersistence.readAll();
    }

}
