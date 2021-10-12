package es.upm.miw.apaw_practice.domain.services.car_hire;

import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.VehiclePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehiclePersistence vehiclePersistence;

    @Autowired
    public VehicleService(VehiclePersistence vehiclePersistence) {
        this.vehiclePersistence = vehiclePersistence;
    }

    public Vehicle updateVehicle(String vinNumber, Vehicle vehicle) {
        Vehicle vehicleToUpdate = this.vehiclePersistence.readByVinNumber(vinNumber).toVehicle();
        vehicle.setVinNumber(vehicleToUpdate.getVinNumber());
        return this.vehiclePersistence.update(vehicle);
    }
}
