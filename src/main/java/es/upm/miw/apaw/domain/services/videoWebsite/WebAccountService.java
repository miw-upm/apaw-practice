package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import es.upm.miw.apaw.domain.persistenceports.videoWebsite.WebAccountPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WebAccountService {

    private final WebAccountPersistence webAccountPersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public WebAccountService(WebAccountPersistence webAccountPersistence, UserRestClient userRestClient) {
        this.webAccountPersistence = webAccountPersistence;
        this.userRestClient = userRestClient;
    }

    public WebAccount findById(UUID id) {
        return this.webAccountPersistence.findById(id);
    }

    public Integer obtainTotalViewsByMobile(String mobile) {
        UserDto user = this.userRestClient.readByMobile(mobile);
        return this.webAccountPersistence.findByUserId(user.getId())
                .flatMap(webAccount -> Optional.ofNullable(webAccount.getPublishedVideos())
                        .orElse(List.of())
                        .stream())
                .map(Video::getViews)
                .reduce(0, Integer::sum);

    }
}
