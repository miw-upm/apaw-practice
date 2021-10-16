package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos.VetRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.VetEntity;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.VetPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class VetRepositoryMongodb implements VetPersistence {

    private final VetRepository vetRepository;

    @Autowired
    public VetRepositoryMongodb (VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Vet create(Vet vet) {
        return this.vetRepository
                .save(new VetEntity(vet, new ArrayList<AppointmentEntity>()))
                .toVet();
    }

    @Override
    public boolean existVetNumber(Integer vetNumber){
        return false;
    }


}
