package es.upm.miw.apaw_practice.adapters.mongodb.Class.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.daos.ProfessorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.ProfessorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.PrincipalRefereeEntity;
import es.upm.miw.apaw_practice.domain.models.Class.Professor;
import es.upm.miw.apaw_practice.domain.models.football.PrincipalReferee;
import es.upm.miw.apaw_practice.domain.persistence_ports.Class.ProfessorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("ProfessorPersistence")
public class ProfessorPersistenceMongodb implements ProfessorPersistence {
    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorPersistenceMongodb(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    @Override
    public Stream<Professor> readAll(){
        return this.professorRepository.findAll().stream()
                .map(ProfessorEntity::toProfessor);
    }

    @Override
    public Professor create(Professor professor) {
        return this.professorRepository
                .save(new ProfessorEntity(professor))
                .toProfessor();
    }
}
