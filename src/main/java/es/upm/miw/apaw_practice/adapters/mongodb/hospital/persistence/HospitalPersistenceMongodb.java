package es.upm.miw.apaw_practice.adapters.mongodb.hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hospital.daos.HospitalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities.HospitalEntity;
import es.upm.miw.apaw_practice.domain.models.hospital.Hospital;
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.HospitalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("hospitalPersistence")
public class HospitalPersistenceMongodb implements HospitalPersistence {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalPersistenceMongodb(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Hospital create(Hospital hospital) {
        return this.hospitalRepository
                .save(new HospitalEntity(hospital))
                .toHospital();
    }
}
