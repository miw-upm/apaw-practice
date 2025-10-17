package es.upm.miw.apaw.domain.persistenceports.vehicle;

import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface VehiclePersistence {

    Stream<Vehicle> readByBrand(String brand);

    List<String> findExtraCategoriesByDocumentationName(String documentationName);

    List<String> findUserMobilesByEngineType(String engineType);
}
