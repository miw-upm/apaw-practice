package es.upm.miw.apaw_practice.domain.services.car_workshop;

import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.TyrePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TyreService {

    private final TyrePersistence tyrePersistence;

    @Autowired
    private TyreService(TyrePersistence tyrePersistence){
        this.tyrePersistence = tyrePersistence;
    }

    public Integer deleteManufacturer(String manufacturer) {
        return this.tyrePersistence.delete(manufacturer);
    }
}
