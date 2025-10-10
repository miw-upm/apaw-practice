package es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos.StudentCouncilRepository;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.StudentCouncilEntity;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.StudentCouncilPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("studentCouncilPersistence")
public class StudentCouncilPersistenceMongodb implements StudentCouncilPersistence {
    private final StudentCouncilRepository studentCouncilRepository;

    @Autowired
    public StudentCouncilPersistenceMongodb(StudentCouncilRepository studentCouncilRepository) {
        this.studentCouncilRepository = studentCouncilRepository;
    }

    @Override
    public Optional<StudentCouncil> readById(UUID id) {
        return this.studentCouncilRepository.findById(id).map(StudentCouncilEntity::toStudentCouncil);
    }

    @Override
    public StudentCouncil update(StudentCouncil studentCouncil) {
        StudentCouncilEntity entity = this.studentCouncilRepository
                .findById(studentCouncil.getId())
                .orElseThrow(() -> new RuntimeException("StudentCouncil not found"));
        entity.fromStudentCouncil(studentCouncil);
        return this.studentCouncilRepository.save(entity).toStudentCouncil();
    }
}
