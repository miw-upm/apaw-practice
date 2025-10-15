package es.upm.miw.apaw.adapters.mongodb.videoWebsite.persistence;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.WebAccountRepository;
import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.WebAccountEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import es.upm.miw.apaw.domain.persistenceports.videoWebsite.WebAccountPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("WebAccountPersistence")
public class WebAccountPersistenceMongodb implements WebAccountPersistence{

    private final WebAccountRepository webAccountRepository;
    private static final String COMMENT_NOT_FOUND = "WebAccount Not Found with id: ";

    @Autowired
    public WebAccountPersistenceMongodb(WebAccountRepository webAccountRepository) {
        this.webAccountRepository = webAccountRepository;
    }

    @Override
    public WebAccount findById(UUID id) {
        WebAccountEntity webAccountEntity = this.webAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(COMMENT_NOT_FOUND + id));
        return webAccountEntity.toWebAccount();
    }
}
