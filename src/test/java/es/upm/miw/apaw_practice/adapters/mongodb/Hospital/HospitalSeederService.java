package es.upm.miw.apaw_practice.adapters.mongodb.Hospital;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.PatientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.DoctorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.HospitalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.AppointmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.PatientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class HospitalSeederService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Hospital Initial Load -----------");

        // Crear entidades de pacientes
        PatientEntity[] patients = {
                new PatientEntity(new Patient("John Doe", LocalDate.of(1985, 2, 10), true, "H001")),
                new PatientEntity(new Patient("Jane Smith", LocalDate.of(1990, 5, 25), false, "H002")),
                new PatientEntity(new Patient("Robert Brown", LocalDate.of(1970, 11, 12), true, "H003"))
        };
        this.patientRepository.saveAll(Arrays.asList(patients));

        // Crear entidades de doctores
        DoctorEntity[] doctors = {
                new DoctorEntity("Dr. Alice Johnson", new BigDecimal("5000.00"), "H001"),
                new DoctorEntity("Dr. Mike Williams", new BigDecimal("4500.00"), "H002"),
                new DoctorEntity("Dr. Emily Davis", new BigDecimal("5500.00"), "H003")
        };
        this.doctorRepository.saveAll(Arrays.asList(doctors));

        // Crear entidades de hospitales
        HospitalEntity[] hospitals = {
                new HospitalEntity("General Hospital", "123 Main St", 200, List.of(doctors[0])),
                new HospitalEntity("City Clinic", "456 Elm St", 150, List.of(doctors[1])),
                new HospitalEntity("HealthCare Center", "789 Oak St", 250, List.of(doctors[2]))
        };
        this.hospitalRepository.saveAll(Arrays.asList(hospitals));

        // Crear entidades de citas médicas
        AppointmentEntity[] appointments = {
                new AppointmentEntity(patients[0].getId(), LocalDate.of(2024, 10, 20), LocalTime.of(10, 0), "Room 101"),
                new AppointmentEntity(patients[1].getId(), LocalDate.of(2024, 11, 15), LocalTime.of(9, 30), "Room 102"),
                new AppointmentEntity(patients[2].getId(), LocalDate.of(2024, 12, 5), LocalTime.of(11, 0), "Room 103")
        };
        this.appointmentRepository.saveAll(Arrays.asList(appointments));
    }

    public void deleteAll() {
        this.appointmentRepository.deleteAll();
        this.hospitalRepository.deleteAll();
        this.doctorRepository.deleteAll();
        this.patientRepository.deleteAll();
    }
}
