package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos.AppointmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos.PetRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos.VetRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.DiagnosisEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.PetEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.VetEntity;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class VetClinicSeederService {

    @Autowired
    private VetRepository vetRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PetRepository petRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Vet Clinic Initial Load -----------");
        DiagnosisEntity[] diagnosis = {
                new DiagnosisEntity("diagnosis1", "medicine1", false),
                new DiagnosisEntity("diagnosis2", "", false),
                new DiagnosisEntity("diagnosis3", "medicine3.1, medicine3.2", true),
                new DiagnosisEntity("diagnosis4", "medicine4", false)
        };
        PetEntity[] pets = {
                new PetEntity(0111, 7, "Neko", "Carmen", List.of(diagnosis[0], diagnosis[1])),
                new PetEntity(2222, 1, "Kairo", "Maria", List.of(diagnosis[1])),
                new PetEntity(3333, 10, "Cooper", "", List.of(diagnosis[0], diagnosis[1],
                        diagnosis[3], diagnosis[2])),
                new PetEntity(0444, 12, "Coca", "Pedro", List.of(diagnosis[0], diagnosis[1],
                        diagnosis[3], diagnosis[2])),
                new PetEntity(5555, 3, "Chetto", "Pablo", List.of(diagnosis[0])),
                new PetEntity(0666, 6, "Lilith", "Pedro", List.of(diagnosis[0])),
                new PetEntity(9777, 9, "Makalu", "Gg", List.of(diagnosis[2])),
                new PetEntity(9888, 3, "Makalu", "Mery", List.of(diagnosis[2])),
                new PetEntity(066, 7, "Lua", "Carmen", List.of(diagnosis[0], diagnosis[1])),
        };
        this.petRepository.saveAll(Arrays.asList(pets));

        AppointmentEntity[] appointments = {
                new AppointmentEntity(LocalDate.of(2019, 3, 15), LocalTime.of(16, 15),
                        true, pets[2]),
                new AppointmentEntity(LocalDate.of(2019, 4, 25), LocalTime.of(10,30),
                        true, pets[0]),
                new AppointmentEntity(LocalDate.of(2020, 7, 10), LocalTime.of(19, 25),
                        true, pets[1]),
                new AppointmentEntity(LocalDate.of(2021, 2, 16), LocalTime.of(9, 00),
                        true, pets[1]),
                new AppointmentEntity(LocalDate.of(2021, 12, 4), LocalTime.of(16, 45),
                        false, pets[1]),
                new AppointmentEntity(LocalDate.of(2021, 12, 4), LocalTime.of(17, 45),
                        false, pets[1]),
                new AppointmentEntity(LocalDate.of(2022, 11, 4), LocalTime.of(12, 45),
                        false, pets[6]),
                new AppointmentEntity(LocalDate.of(2021, 11, 8), LocalTime.of(18, 15),
                        false, pets[7])
        };
        this.appointmentRepository.saveAll(Arrays.asList(appointments));

        VetEntity[] vets = {
                new VetEntity(Vet.builder().vetNumber(111).name("vet1").surname("surname1").build(),
                        List.of(appointments[0], appointments[1], appointments[6], appointments[7])),
                new VetEntity(Vet.builder().vetNumber(222).name("vet2").surname("").build(),
                        List.of(appointments[0], appointments[1])),
                new VetEntity(Vet.builder().vetNumber(333).name("vet3").surname("surname3").build(),
                        List.of()),
                new VetEntity(Vet.builder().vetNumber(444).name("").surname("surname4").build(),
                        List.of(appointments[2], appointments[3], appointments[4], appointments[5]))
        };
        this.vetRepository.saveAll(Arrays.asList(vets));
    }

    public void deleteAll(){
        this.petRepository.deleteAll();
        this.appointmentRepository.deleteAll();
        this.vetRepository.deleteAll();
    }
}
