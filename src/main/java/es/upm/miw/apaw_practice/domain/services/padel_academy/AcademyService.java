package es.upm.miw.apaw_practice.domain.services.padel_academy;

import es.upm.miw.apaw_practice.domain.models.padel_academy.Academy;
import es.upm.miw.apaw_practice.domain.models.padel_academy.Instructor;
import es.upm.miw.apaw_practice.domain.persistence_ports.padel_academy.AcademyPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.padel_academy.InstructorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademyService {
    private final AcademyPersistence academyPersistence;
    private final InstructorPersistence instructorPersistence;

    @Autowired
    public AcademyService(AcademyPersistence academyPersistence, InstructorPersistence instructorPersistence) {
        this.academyPersistence = academyPersistence;
        this.instructorPersistence = instructorPersistence;
    }

    public Academy read(String name) {
        return this.academyPersistence.readByName(name);
    }

    public void delete(String name) {
        this.academyPersistence.deleteByName(name);
    }

    public Academy updateAddress(String name, String address) {
        Academy academy = this.academyPersistence.readByName(name);
        academy.setAddress(address);
        return this.academyPersistence.updateAddress(academy);
    }

    public List<String> findAcademyAddressByInstructorName(String name) {
        Instructor myInstructor =  this.instructorPersistence.readByName(name);
        return this.academyPersistence.readAll()
                .filter(academy -> academy.getInstructors()
                        .stream().anyMatch(instructor ->  instructor.getName().equals(myInstructor.getName())))
                .distinct()
                .map(Academy::getAddress)
                .toList();
    }
}
