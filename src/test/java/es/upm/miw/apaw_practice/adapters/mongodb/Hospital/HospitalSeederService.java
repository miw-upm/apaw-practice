package es.upm.miw.apaw_practice.adapters.mongodb.Hospital;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.AppointmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.DoctorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@Service
public class HospitalSeederService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Hospital Initial Load -----------");

        // Seed Doctors
        DoctorEntity[] doctors = {
                new DoctorEntity("DNI001", "Dr. Alice Smith", new BigDecimal("5000.00")),
                new DoctorEntity("DNI002", "Dr. Bob Johnson", new BigDecimal("6000.00")),
                new DoctorEntity("DNI003", "Dr. Carol Williams", new BigDecimal("5500.00")),
                new DoctorEntity("DNI004", "Dr. David Brown", new BigDecimal("5200.00"))
        };
        this.doctorRepository.saveAll(Arrays.asList(doctors));

        // Seed Appointments
        AppointmentEntity[] appointments = {
                new AppointmentEntity(1, LocalDate.of(2024, 10, 20), LocalTime.of(9, 30), "Room 101", "PAT001", doctors[0].getDni()),
                new AppointmentEntity(2, LocalDate.of(2024, 10, 21), LocalTime.of(11, 15), "Room 102", "PAT002", doctors[1].getDni()),
                new AppointmentEntity(3, LocalDate.of(2024, 10, 22), LocalTime.of(14, 0), "Room 103", "PAT003", doctors[2].getDni()),
                new AppointmentEntity(4, LocalDate.of(2024, 10, 23), LocalTime.of(16, 30), "Room 104", "PAT004", doctors[3].getDni())
        };
        this.appointmentRepository.saveAll(Arrays.asList(appointments));
    }

    public void deleteAll() {
        this.appointmentRepository.deleteAll();
        this.doctorRepository.deleteAll();
    }
}
