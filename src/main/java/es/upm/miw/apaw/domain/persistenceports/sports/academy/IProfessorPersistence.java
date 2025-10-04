package es.upm.miw.apaw.domain.persistenceports.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.Professor;

import java.util.UUID;
import java.util.stream.Stream;

public interface IProfessorPersistence {
    Stream<Professor> getAll(int page, int pageSize);

    Professor create(Professor professor);

    Professor update(UUID id, Professor professor);

    Professor getById(UUID id);
}
