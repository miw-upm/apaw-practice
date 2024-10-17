package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.PatientRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.PatientPersistence;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.PatientEntity;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatientPersistenceMongodb implements PatientPersistence {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientPersistenceMongodb(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void delete(String dni) {
        this.patientRepository.deleteByDni(dni);
    }

    @Override
    public Patient updateName(String dni, String name) {

        PatientEntity patientEntity = this.patientRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Patient not found"));


        patientEntity.setFullname(name);


        return this.patientRepository.save(patientEntity).toClient();
    }
}
