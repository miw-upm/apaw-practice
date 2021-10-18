package es.upm.miw.apaw_practice.domain.services.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Hospital;
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.HospitalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    private final HospitalPersistence hospitalPersistence;

    @Autowired
    public HospitalService(HospitalPersistence hospitalPersistence) {
        this.hospitalPersistence = hospitalPersistence;
    }

    public Hospital create(Hospital hospital) {
        return this.hospitalPersistence.create(hospital);
    }
}
