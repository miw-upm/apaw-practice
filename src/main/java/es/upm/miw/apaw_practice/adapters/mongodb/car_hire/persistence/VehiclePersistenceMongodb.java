package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.VehicleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.VehicleEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.VehiclePersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;

@Repository("vehiclePersistence")
public class VehiclePersistenceMongodb implements VehiclePersistence {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehiclePersistenceMongodb(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle readByVinNumber(String vinNumber) {
        return this.vehicleRepository
                .findByVinNumber(vinNumber)
                .orElseThrow(() -> new NotFoundException("Vehicle VIN_Number: " + vinNumber))
                .toVehicle();
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        assertTrue(this.vehicleRepository.findByVinNumber(vehicle.getVinNumber()).isPresent());
        VehicleEntity vehicleEntity = this.vehicleRepository
                .findByVinNumber(vehicle.getVinNumber()).get();
        BeanUtils.copyProperties(vehicle, vehicleEntity, "id", "vinNumber");
        return this.vehicleRepository.save(vehicleEntity).toVehicle();
    }
}
