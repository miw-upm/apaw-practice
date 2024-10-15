package es.upm.miw.apaw_practice.domain.persistence_ports.martial_art;

import es.upm.miw.apaw_practice.domain.models.martial_art.MartialArtsClass;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface MartialArtsClassPersistence {

    Stream<MartialArtsClass> readAll();

    MartialArtsClass create(MartialArtsClass martialArtsClass);

    MartialArtsClass read(String name);

    boolean existsByName(String name);

    void delete(String name);
}
