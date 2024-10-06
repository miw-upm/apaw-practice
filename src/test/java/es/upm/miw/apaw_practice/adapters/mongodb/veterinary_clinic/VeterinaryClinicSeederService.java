package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.AnimalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.ClinicRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.EmployeeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.OwnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.AnimalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.ClinicEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.EmployeeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.OwnerEntity;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Owner;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class VeterinaryClinicSeederService {

    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("-------- Veterinary Clinic Initial Load --------");

        OwnerEntity[] owners = {
                new OwnerEntity(new Owner("Marcos", "Street Toledo", "980453215")),
                new OwnerEntity(new Owner("Juan", "Street Segundo", "841256798")),
                new OwnerEntity(new Owner("Mary", "Street Principal", "852693147")),
                new OwnerEntity(new Owner("Aitana", "Street San Juan", "651234879"))
        };
        this.ownerRepository.saveAll(Arrays.asList(owners));

        AnimalEntity[] animals = {
                new AnimalEntity("Lara", 10,
                        LocalDateTime.of(2014, 8, 10, 15, 10), owners[0]),
                new AnimalEntity("Pingo", 8,
                        LocalDateTime.of(2016, 5, 2, 10, 11), owners[1]),
                new AnimalEntity("Toy", 5,
                        LocalDateTime.of(2017, 7, 4, 2, 58), owners[2]),
                new AnimalEntity("Mel", 2,
                        LocalDateTime.of(2022, 10, 11, 5, 41), owners[3])
        };
        this.animalRepository.saveAll(Arrays.asList(animals));

        EmployeeEntity[] employees = {
                new EmployeeEntity("Paco", true, List.of(animals[0])),
                new EmployeeEntity("Daniela", false, List.of(animals[1])),
                new EmployeeEntity("María", true, List.of(animals[2])),
                new EmployeeEntity("Juan", true, List.of(animals[3]))
        };
        this.employeeRepository.saveAll(Arrays.asList(employees));

        ClinicEntity[] clinics = {
                new ClinicEntity("Veterinary Clinic Happy Life", "Street San Francisco",
                        List.of(employees[0])),
                new ClinicEntity("Veterinary Clinic Pets", "Street New York",
                        List.of(employees[1])),
                new ClinicEntity("Veterinary Clinic Heal", "Street Michgan",
                        List.of(employees)),
                new ClinicEntity("Veterinary Clinic Animal", "Street Orlando",
                        List.of(employees))
        };
        this.clinicRepository.saveAll(Arrays.asList(clinics));
    }

    public void deleteAll() {
        this.ownerRepository.deleteAll();
        this.animalRepository.deleteAll();
        this.employeeRepository.deleteAll();
        this.clinicRepository.deleteAll();
    }
}