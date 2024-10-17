package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.PatientRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.PatientPersistence;
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
        patientRepository.deleteByDni(dni);
    }
    @Override
    public Patient updateName(String dni, String name) {
        PatientEntity PatientEntity = this.patientRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException(" patient dni: " + dni));
        PatientEntity.setName(name);
        return this.patientRepository.save(PatientEntity).toClient();
    }


}
