package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.PatientRepository;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.PatientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientPersistenceMongodb implements PatientPersistence {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientPersistenceMongodb(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll(); 
    }

    @Override
    public void delete(String id) {
        patientRepository.deleteById(id);
    }
}
