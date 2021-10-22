package es.upm.miw.apaw_practice.domain.services.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Doctor;
import es.upm.miw.apaw_practice.domain.models.hospital.Patient;
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.DoctorPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.hospital.PatientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DoctorService {

    private final DoctorPersistence doctorPersistence;
    private final PatientPersistence patientPersistence;

    @Autowired
    public DoctorService(DoctorPersistence doctorPersistence, PatientPersistence patientPersistence){
        this.doctorPersistence = doctorPersistence;
        this.patientPersistence = patientPersistence;
    }

    public Stream<Doctor> readDoctorNicks() {
        return this.doctorPersistence.readNicks();
    }

    public Stream<Doctor> findSurnamesByDiseaseSeverity(Boolean severity) {
        return this.patientPersistence.findAllWithDiseaseSeverity(severity).stream()
                .map(Patient::getDoctor)
                .map(doctor -> Doctor.builder()
                        .nick(null)
                        .surname(doctor.getSurname())
                        .activeSince(null)
                        .build())
                .distinct();
    }
}
