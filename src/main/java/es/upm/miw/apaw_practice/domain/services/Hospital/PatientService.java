package es.upm.miw.apaw_practice.domain.services.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.PatientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientPersistence patientPersistence;

    @Autowired
    public PatientService(PatientPersistence patientPersistence) {
        this.patientPersistence = patientPersistence;
    }


    public void delete(String dni) {
        patientPersistence.delete(dni);
    }

}
