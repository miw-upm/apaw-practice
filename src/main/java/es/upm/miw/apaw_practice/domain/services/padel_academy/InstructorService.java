package es.upm.miw.apaw_practice.domain.services.padel_academy;

import es.upm.miw.apaw_practice.domain.models.padel_academy.Academy;
import es.upm.miw.apaw_practice.domain.models.padel_academy.Instructor;
import es.upm.miw.apaw_practice.domain.persistence_ports.padel_academy.AcademyPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.padel_academy.InstructorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorPersistence instructorPersistence;
    private final AcademyPersistence academyPersistence;

    @Autowired
    public InstructorService(InstructorPersistence instructorPersistence, AcademyPersistence academyPersistence) {
        this.instructorPersistence = instructorPersistence;
        this.academyPersistence = academyPersistence;
    }

    public Instructor updatePhoneNumber(String dni, Integer phoneNumber) {
        Instructor instructor = this.instructorPersistence.read(dni);
        instructor.setPhoneNumber(phoneNumber);
        return this.instructorPersistence.update(instructor);
    }

    public List<String> findInstructorsNamesByCourtSurface(String surface) {
        return this.academyPersistence.readAll()
                .filter(academy -> academy.getCourts()
                        .stream().anyMatch(court -> court.getSurface().equals(surface)))
                .flatMap(academy -> academy.getInstructors().stream())
                .distinct()
                .map(Instructor::getName)
                .toList();
    }

    public Instructor read(String name) {
        return this.instructorPersistence.readByName(name);
    }
}
