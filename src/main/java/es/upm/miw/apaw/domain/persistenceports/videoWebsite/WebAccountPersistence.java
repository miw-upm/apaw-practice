package es.upm.miw.apaw.domain.persistenceports.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface WebAccountPersistence {
    WebAccount findById(UUID id);
    Stream<WebAccount> findByUserId(UUID id);
}
