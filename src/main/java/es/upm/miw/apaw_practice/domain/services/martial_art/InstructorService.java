package es.upm.miw.apaw_practice.domain.services.martial_art;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import es.upm.miw.apaw_practice.domain.persistence_ports.martial_art.InstructorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {
    private final InstructorPersistence instructorPersistence;

    @Autowired
    public InstructorService(InstructorPersistence instructorPersistence) {
        this.instructorPersistence = instructorPersistence;
    }

    public Instructor create(Instructor instructor) {
        this.assertNIFNotExists(instructor.getDni());
        return this.instructorPersistence.create(instructor);
    }

    private void assertNIFNotExists(String dni) {
        if (this.instructorPersistence.existsByDni(dni)) {
            throw new ConflictException("dni exists: " + dni);
        }
    }

    public Instructor read(String dni) {
        return this.instructorPersistence.read(dni);
    }

    public void delete(String dni) {
        this.instructorPersistence.delete(dni);
    }

    public Instructor update(String dni, Instructor instructor) {
        return this.instructorPersistence.update(dni, instructor);
    }
}
