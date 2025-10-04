package es.upm.miw.apaw.adapters.mongodb.sports.academy.persistence;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.daos.IProfessorRepository;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.ProfessorEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import es.upm.miw.apaw.domain.persistenceports.sports.academy.IProfessorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository("professorPersistence")
public class ProfessorPersistenceMongodb implements IProfessorPersistence {
    private final IProfessorRepository professorRepository;

    @Autowired
    public ProfessorPersistenceMongodb(IProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Stream<Professor> getAll(int page, int pageSize) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return this.professorRepository
                .findAll(pageable)
                .stream()
                .map(ProfessorEntity::toProfessor);
    }

    @Override
    public Professor create(Professor professor) {
        return this.professorRepository
                .save(new ProfessorEntity(professor))
                .toProfessor();
    }

    @Override
    public Professor update(UUID id, Professor professor) {
        ProfessorEntity professorEntity = this.professorRepository
                .findByUserDtoId(professor.getUser().getId())
                .orElseThrow(() -> new NotFoundException("Professor user dto id: " + id));
        professorEntity.fromProfessor(professor);
        return this.professorRepository
                .save(professorEntity)
                .toProfessor();
    }

    @Override
    public Professor getById(UUID id) {
        return this.professorRepository
                .findByUserDtoId(id)
                .orElseThrow(() -> new NotFoundException("Professor user dto id: " + id))
                .toProfessor();
    }
}
