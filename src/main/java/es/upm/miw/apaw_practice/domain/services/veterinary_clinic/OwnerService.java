package es.upm.miw.apaw_practice.domain.services.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.OwnerClinic;
import es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic.OwnerClinicPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class OwnerService {

    private final OwnerClinicPersistence ownerClinicPersistence;

    @Autowired
    public OwnerService(OwnerClinicPersistence ownerClinicPersistence) {
        this.ownerClinicPersistence = ownerClinicPersistence;
    }

    public OwnerClinic create(OwnerClinic ownerClinic) {
        this.assertNameNotExist(ownerClinic.getName());
        return this.ownerClinicPersistence.create(ownerClinic);
    }

    private void assertNameNotExist(String name) {
        if(this.ownerClinicPersistence.existName(name)) {
            throw new ConflictException("Name exist: " + name);
        }
    }

    public void updateOwner(Stream<OwnerClinic> ownerUpdatingList) {
        ownerUpdatingList.map(ownerNewName -> {
            OwnerClinic ownerClinic = this.ownerClinicPersistence.read(ownerNewName.getName());
            ownerClinic.setName(ownerNewName.getName());
            return ownerClinic;
        })
                .forEach(owner -> this.ownerClinicPersistence.update(owner.getAddress(), owner.getPhone(), owner));
    }
}