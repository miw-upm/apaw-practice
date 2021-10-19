package es.upm.miw.apaw_practice.domain.services.Class;

import es.upm.miw.apaw_practice.domain.models.Class.Professor;
import es.upm.miw.apaw_practice.domain.persistence_ports.Class.ProfessorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ProfessorService {
    private final ProfessorPersistence professorPersistence;

    @Autowired
    ProfessorService(ProfessorPersistence professorPersistence){
        this.professorPersistence = professorPersistence;
    }

    public Stream<Professor> readAll() {return this.professorPersistence.readAll();}
}
