package es.upm.miw.apaw.domain.persistenceports.apiary;

import es.upm.miw.apaw.domain.models.apiary.Apiary;
import org.springframework.stereotype.Repository;
import java.util.stream.Stream;


@Repository
public interface ApiaryPersistence {
    Stream<Apiary> findByLocation(String location);

}
