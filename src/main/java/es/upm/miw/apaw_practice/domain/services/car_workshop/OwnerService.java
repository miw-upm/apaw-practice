package es.upm.miw.apaw_practice.domain.services.car_workshop;

import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.OwnerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final OwnerPersistence ownerPersistence;

    @Autowired
    public OwnerService(OwnerPersistence ownerPersistence){
        this.ownerPersistence = ownerPersistence;
    }

    public Owner readByDni(String dni) {
        return this.ownerPersistence.readByDni(dni);
    }
}
