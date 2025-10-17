package es.upm.miw.apaw.domain.services.sports.academy;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.IProfessorPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    private final IProfessorPersistence professorPersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public ProfessorService(
            IProfessorPersistence professorPersistence,
            UserRestClient userRestClient) {
        this.professorPersistence = professorPersistence;
        this.userRestClient = userRestClient;
    }

    public Professor create(Professor professor) {
        UserDto userDto = this.userRestClient.readById(professor.getUser().getId());
        professor.setUser(userDto);
        return this.professorPersistence.create(professor);
    }
}
