package es.upm.miw.apaw_practice.domain.persistence_ports.training;

import org.springframework.stereotype.Repository;

@Repository
public interface CoursePersistence {
    void deleteById(String id);
}
