package es.upm.miw.apaw_practice.domain.services.Class;

import es.upm.miw.apaw_practice.domain.persistence_ports.Class.ClassPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ClassService {
    private final ClassPersistence classPersistence;

    @Autowired
    public ClassService(ClassPersistence classPersistence) {
        this.classPersistence = classPersistence;
    }

    public Stream<es.upm.miw.apaw_practice.domain.models.Class.Class> readAll() {
        return this.classPersistence.readAll();
    }
}
