package es.upm.miw.apaw.domain.services.vehicle;

import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import es.upm.miw.apaw.domain.persistenceports.vehicle.VehiclePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class VehicleService {

    private final VehiclePersistence vehiclePersistence;

    @Autowired
    public VehicleService(VehiclePersistence vehiclePersistence) {
        this.vehiclePersistence = vehiclePersistence;
    }

    public Stream<Vehicle> findByBrand(String brand) {
        return this.vehiclePersistence.readByBrand(brand);
    }
}
