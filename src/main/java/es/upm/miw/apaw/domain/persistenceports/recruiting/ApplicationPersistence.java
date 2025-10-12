package es.upm.miw.apaw.domain.persistenceports.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Application;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ApplicationPersistence {

    Application readById(UUID id);

    List<Application> readAll();

    Application update(Application application);

    // First search: 1269
    BigDecimal findAccumulatedAnnualSalaryByFullName(String fullName);

    // Second search: 1270
    List<String> findUniqueUrlsByPositionName(String name);
}