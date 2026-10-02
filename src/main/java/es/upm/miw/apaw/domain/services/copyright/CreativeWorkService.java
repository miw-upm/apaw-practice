package es.upm.miw.apaw.domain.services.copyright;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
import es.upm.miw.apaw.domain.model.copyright.CreativeWorkCreation;
import es.upm.miw.apaw.domain.ports.out.copyright.CreativeWorkGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreativeWorkService {
    private final CreativeWorkGateway creativeWorkGateway;
    private final UserFinder userFinder;

    public CreativeWork create(CreativeWorkCreation creation) {
        if (this.creativeWorkGateway.existsByRegistrationCode(creation.getRegistrationCode())) {
            throw new ConflictException("Registration code already exists: " + creation.getRegistrationCode());
        }

        CreativeWork creativeWork = new CreativeWork();
        BeanUtils.copyProperties(creation, creativeWork, "authorId");
        creativeWork.setAuthor(this.userFinder.read(creation.getAuthorId()));
        creativeWork.doDefault();

        return this.creativeWorkGateway.create(creativeWork);
    }
}
