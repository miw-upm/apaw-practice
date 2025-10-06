package es.upm.miw.apaw.domain.services.apiary;

import es.upm.miw.apaw.domain.models.apiary.Apiary;
import es.upm.miw.apaw.domain.persistenceports.apiary.ApiaryPersistence;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;



@Service
public class ApiaryService {

    private final ApiaryPersistence apiaryPersistence;

    @Autowired
    public ApiaryService(ApiaryPersistence apiaryPersistence) {
        this.apiaryPersistence = apiaryPersistence;
    }

    public Stream<Apiary> findByLocation(String location) {
        return this.apiaryPersistence.findByLocation(location);
    }
}
