package es.upm.miw.apaw_practice.domain.services.vet_clinic;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.VetPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VetService {
    public final VetPersistence vetPersistence;

    @Autowired
    public VetService (VetPersistence vetPersistence){
        this.vetPersistence = vetPersistence;
    }

    public Vet create(Vet vet) {
        this.assertVetNumberNotExist(vet.getVetNumber());
        return this.vetPersistence.create(vet);
    }

    public void assertVetNumberNotExist(Integer vetNumber){
        if (this.vetPersistence.existVetNumber(vetNumber)) {
            throw new ConflictException("VetNumber exist: " + vetNumber);
        }
    }
}
