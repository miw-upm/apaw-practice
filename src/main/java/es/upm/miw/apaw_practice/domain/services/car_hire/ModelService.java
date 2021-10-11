package es.upm.miw.apaw_practice.domain.services.car_hire;

import es.upm.miw.apaw_practice.domain.models.car_hire.Model;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.ModelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    private final ModelPersistence modelPersistence;

    @Autowired
    public ModelService(ModelPersistence modelPersistence) {
        this.modelPersistence = modelPersistence;
    }

    public Model findModelByVehicleVinNumber(String vinNumber) {
        return this.modelPersistence.readByVinNumber(vinNumber);
    }
}
