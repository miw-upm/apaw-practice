package es.upm.miw.apaw_practice.domain.persistence_ports.military;

import es.upm.miw.apaw_practice.domain.models.military.Mission;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface MissionPersistence {
    Mission update(String codeName, Mission mission);
    Stream<Mission> readAll();
}
