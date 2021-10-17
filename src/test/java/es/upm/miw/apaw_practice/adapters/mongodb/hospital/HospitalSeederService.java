package es.upm.miw.apaw_practice.adapters.mongodb.hospital;

import es.upm.miw.apaw_practice.adapters.mongodb.hospital.daos.DiseaseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.daos.DoctorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.daos.HospitalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.daos.PatientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities.DiseaseEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities.DoctorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities.HospitalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities.PatientEntity;
import es.upm.miw.apaw_practice.domain.models.hospital.Disease;
import es.upm.miw.apaw_practice.domain.models.hospital.Doctor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class HospitalSeederService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Hospital Initial Load -----------");
        DoctorEntity[] doctors = {
                new DoctorEntity(new Doctor("John", "Doe", LocalDate.of(1989,11,23))),
                new DoctorEntity(new Doctor("Marta", "Lopez", LocalDate.of(1999,4,2))),
                new DoctorEntity(new Doctor("Jose", "Vazquez", LocalDate.of(1980,6,5)))
        };
        this.doctorRepository.saveAll(Arrays.asList(doctors));

        DiseaseEntity[] diseases = {
                new DiseaseEntity(new Disease("Mild coughing and fever", Boolean.FALSE,"Common cold")),
                new DiseaseEntity(new Disease("Severe organ failure", Boolean.TRUE,"Kidney failure")),
                new DiseaseEntity(new Disease("Eye membrane inflamation", Boolean.FALSE,"Conjunctivitis"))
        };
        this.diseaseRepository.saveAll(Arrays.asList(diseases));

        PatientEntity[] patients = {
                new PatientEntity("03457384C", "Male", 23, List.of(diseases[1], diseases[0]), doctors[1]),
                new PatientEntity("03468384F", "Female", 23, List.of(diseases[2], diseases[0]), doctors[0]),
                new PatientEntity("12357798M", "Male", 23, List.of(diseases[1]), doctors[2])
        };
        this.patientRepository.saveAll(Arrays.asList(patients));

        HospitalEntity[] hospitals = {
                new HospitalEntity("Los Angeles Community Hospital", "E Olympic Blvd", 300, List.of(patients[0],patients[1])),
                new HospitalEntity("Kindred Hospital Los Angeles", "W Slauson Ave", 400, List.of(patients[0],patients[2])),
                new HospitalEntity("LAC+USC Medical Center", "Marengo Street", 200, List.of(patients[1],patients[2]))
        };
        this.hospitalRepository.saveAll(Arrays.asList(hospitals));
    }

    public void deleteAll() {
        this.doctorRepository.deleteAll();
        this.patientRepository.deleteAll();
        this.hospitalRepository.deleteAll();
        this.diseaseRepository.deleteAll();
    }
}
