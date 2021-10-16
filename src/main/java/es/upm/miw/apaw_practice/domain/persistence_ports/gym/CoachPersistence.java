package es.upm.miw.apaw_practice.domain.persistence_ports.gym;


import org.springframework.stereotype.Repository;

@Repository
public interface CoachPersistence {
    void delete(String dni);
}
