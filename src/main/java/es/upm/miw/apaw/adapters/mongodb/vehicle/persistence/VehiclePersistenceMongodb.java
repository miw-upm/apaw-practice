package es.upm.miw.apaw.adapters.mongodb.vehicle.persistence;

import es.upm.miw.apaw.adapters.mongodb.vehicle.daos.VehicleRepository;
import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.VehicleEntity;
import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import es.upm.miw.apaw.domain.persistenceports.vehicle.VehiclePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("vehiclePersistence")
public class VehiclePersistenceMongodb implements VehiclePersistence {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehiclePersistenceMongodb(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Stream<Vehicle> readByBrand(String brand) {
        return this.vehicleRepository.findByBrand(brand).stream()
                .map(VehicleEntity::toVehicle);
    }

}
