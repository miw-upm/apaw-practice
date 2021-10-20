package es.upm.miw.apaw_practice.adapters.mongodb.Class.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.daos.ClassRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.ClassEntity;
import es.upm.miw.apaw_practice.domain.models.Class.Class;
import es.upm.miw.apaw_practice.domain.persistence_ports.Class.ClassPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("ClassPersistence")
public class ClassPersistenceMongodb implements ClassPersistence {

    private final ClassRepository classrepository;

    @Autowired
    public ClassPersistenceMongodb(ClassRepository classrepository) {
        this.classrepository = classrepository;
    }

    @Override
    public Stream<es.upm.miw.apaw_practice.domain.models.Class.Class> readAll() {
        return this.classrepository.findAll().stream()
                .map(ClassEntity::toClass);
    }

    @Override
    public Class create(Class myClass){
        return this.classrepository
                .save(new ClassEntity(myClass))
                .toClass();

    }

}

