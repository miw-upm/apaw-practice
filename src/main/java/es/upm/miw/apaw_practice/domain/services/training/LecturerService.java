package es.upm.miw.apaw_practice.domain.services.training;

import es.upm.miw.apaw_practice.domain.models.training.Lecturer;
import es.upm.miw.apaw_practice.domain.persistence_ports.training.LecturerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturerService {
    private final LecturerPersistence lecturerPersistence;

    @Autowired
    public LecturerService(LecturerPersistence lecturerPersistence) {
        this.lecturerPersistence = lecturerPersistence;
    }

    public void updateExperience(String dni, Lecturer lecturer) {
        Lecturer lecturerDto = this.lecturerPersistence.readByDni(dni);
        lecturerDto.setExperience(lecturer.getExperience());
        this.lecturerPersistence.update(lecturerDto);
    }
}
