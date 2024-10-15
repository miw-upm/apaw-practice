package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.HospitalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntity;
import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.HospitalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("hospitalPersistence")
public class HospitalPersistenceMongodb implements HospitalPersistence {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalPersistenceMongodb(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }
    @Override
    public boolean existsByName(String name) {
        return hospitalRepository.existsByName(name);
    }



    @Override
    public Hospital create(Hospital hospital) {
        // Convert Hospital model to HospitalEntity and save it in the database
        HospitalEntity hospitalEntity = new HospitalEntity(hospital);
        return this.hospitalRepository.save(hospitalEntity).toHospital();
    }


}
