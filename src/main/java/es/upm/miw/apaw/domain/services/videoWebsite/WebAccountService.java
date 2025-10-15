package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import es.upm.miw.apaw.domain.persistenceports.videoWebsite.WebAccountPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WebAccountService {

    private final WebAccountPersistence webAccountPersistence;

    @Autowired
    public WebAccountService(WebAccountPersistence webAccountPersistence) {
        this.webAccountPersistence = webAccountPersistence;
    }

    public WebAccount findById(UUID id) {
        return this.webAccountPersistence.findById(id);
    }
}
