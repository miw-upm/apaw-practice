package es.upm.miw.apaw_practice.domain.services.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Owner;
import es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic.OwnerClinicPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final OwnerClinicPersistence ownerClinicPersistence;

    @Autowired
    public OwnerService(OwnerClinicPersistence ownerClinicPersistence) {
        this.ownerClinicPersistence = ownerClinicPersistence;
    }

    public Owner create(Owner owner) {
        this.assertNameNotExist(owner.getName());
        return this.ownerClinicPersistence.create(owner);
    }

    private void assertNameNotExist(String name) {
        if(this.ownerClinicPersistence.existName(name)) {
            throw new ConflictException("Name exist: " + name);
        }
    }
}