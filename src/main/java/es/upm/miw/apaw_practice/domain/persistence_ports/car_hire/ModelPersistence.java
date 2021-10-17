package es.upm.miw.apaw_practice.domain.persistence_ports.car_hire;

import es.upm.miw.apaw_practice.domain.models.car_hire.Model;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ModelPersistence {

    Model readByVinNumber(String vinNumber);

    Stream<Model> readAll();
}
