package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.VehicleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.VehicleEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.VehiclePersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        VehicleEntity vehicleEntity = this.vehicleRepository
                .findByVinNumber(vehicle.getVinNumber())
                .orElseThrow(() -> new NotFoundException("Vehicle VIN_Number: " + vehicle.getVinNumber()));
        BeanUtils.copyProperties(vehicle, vehicleEntity, "id", "vinNumber");
        return this.vehicleRepository.save(vehicleEntity).toVehicle();
    }
}
