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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
                new OwnerEntity(new Owner("Marcos", "Calle Toledo", "980453215")),
                new OwnerEntity(new Owner("Juan", "Calle Segundo", "841256798")),
                new OwnerEntity(new Owner("María", "Calle Principal", "852693147")),
                new OwnerEntity(new Owner("Aitana", "Calle San Juan", "651234879"))
        };
        this.ownerRepository.saveAll(Arrays.asList(owners));

        AnimalEntity[] animals = {
                new AnimalEntity(owners[0], "Lara", 10,
                        LocalDateTime.of(2014, 8, 10, 15, 10)),
                new AnimalEntity(owners[1], "Pingo", 8,
                        LocalDateTime.of(2016, 5, 2, 10, 11)),
                new AnimalEntity(owners[2], "Toy", 5,
                        LocalDateTime.of(2017, 7, 4, 2, 58)),
                new AnimalEntity(owners[3], "Mel", 2,
                        LocalDateTime.of(2022, 10, 11, 5, 41))
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
                new ClinicEntity("Clínica Veterinaria Vida Animal", "Calle San Francisco",
                        List.of(employees[0])),
                new ClinicEntity("Clínica Veterinaria Mascotas Felices", "Calle Gran Via",
                        List.of(employees[1])),
                new ClinicEntity("Clínica Veterinaria Tu Cura", "Calle Tercera",
                        List.of(employees)),
                new ClinicEntity("Clínica Veterinaria Vida Animal", "Calle Segovia",
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