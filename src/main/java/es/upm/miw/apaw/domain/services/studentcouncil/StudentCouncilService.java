package es.upm.miw.apaw.domain.services.studentcouncil;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentCouncil;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.StudentCouncilPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class StudentCouncilService {

    private final StudentCouncilPersistence studentCouncilPersistence;
    private static final Logger log = LoggerFactory.getLogger(StudentCouncilService.class);

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
        var councils = this.studentCouncilPersistence.readAll().toList();
        return councils.stream()
                .filter(c -> c.getRepresentatives() != null)
                .filter(c -> c.getRepresentatives().stream()
                        .anyMatch(r -> r.getTopics() != null &&
                                r.getTopics().stream()
                                        .anyMatch(i -> {
                                            boolean match = statement.equalsIgnoreCase(i.getStatement());
                                            if (match) log.warn("Matched [{}] in {}", statement, c.getCouncil());
                                            return match;
                                        })
                        )
                )
                .map(StudentCouncil::getResources)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}