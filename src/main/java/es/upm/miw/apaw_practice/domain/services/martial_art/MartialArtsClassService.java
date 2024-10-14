package es.upm.miw.apaw_practice.domain.services.martial_art;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.martial_art.MartialArtsClass;
import es.upm.miw.apaw_practice.domain.persistence_ports.martial_art.MartialArtsClassPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MartialArtsClassService {
    private final MartialArtsClassPersistence martialArtsClassPersistence;

    @Autowired
    public MartialArtsClassService(MartialArtsClassPersistence martialArtsClassPersistence) {
        this.martialArtsClassPersistence = martialArtsClassPersistence;
    }

    public MartialArtsClass create(MartialArtsClass martialArtsClass) {
        return this.martialArtsClassPersistence.create(martialArtsClass);
    }

}
