package es.upm.miw.apaw.domain.services.studentcouncil;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.StudentCouncilPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class StudentCouncilService {

    private final StudentCouncilPersistence studentCouncilPersistence;

    @Autowired
    public StudentCouncilService(StudentCouncilPersistence studentCouncilPersistence) {
        this.studentCouncilPersistence = studentCouncilPersistence;
    }

    public StudentCouncil updateResources(UUID id, BigDecimal newResources) {
        StudentCouncil council = this.studentCouncilPersistence.readById(id)
                .orElseThrow(() -> new NotFoundException("StudentCouncil id not found: " + id));
        council.setResources(newResources);
        return this.studentCouncilPersistence.update(council);
    }

    public BigDecimal sumResourcesByStatement(String statement) {
        return this.studentCouncilPersistence.readAll()
                .filter(council -> council.getRepresentatives() != null)
                .filter(council -> council.getRepresentatives().stream()
                        .filter(rep -> rep.getTopics() != null)
                        .anyMatch(rep -> rep.getTopics().stream()
                                .anyMatch(issue -> statement.equalsIgnoreCase(issue.getStatement()))
                        )
                )
                .map(StudentCouncil::getResources)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}