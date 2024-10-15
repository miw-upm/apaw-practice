package es.upm.miw.apaw_practice.domain.services.martial_art;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import es.upm.miw.apaw_practice.domain.persistence_ports.martial_art.TechniquePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechniqueService {
    private final TechniquePersistence techniquePersistence;

    @Autowired
    public TechniqueService(TechniquePersistence techniquePersistence) {
        this.techniquePersistence = techniquePersistence;
    }

    public Technique create(Technique technique) {return this.techniquePersistence.create(technique);}
    public void delete(String name) {
        this.techniquePersistence.delete(name);
    }

}
