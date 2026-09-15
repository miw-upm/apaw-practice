package es.upm.miw.apaw.adapters.out.user.feign;

import es.upm.miw.apaw.domain.exceptions.BadGatewayException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserSnapshot;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserFinderAdapter implements UserFinder {
    private final ApawUserClient apawUserClient;

    @Override
    public UserSnapshot read(UUID id) {
        try {
            UserResponse response = this.apawUserClient.read(id);
            return UserSnapshot.builder()
                    .id(response.id())
                    .mobile(response.mobile())
                    .firstName(response.firstName())
                    .build();
        } catch (FeignException exception) {
            if (exception.status() == 404) {
                throw new NotFoundException("User id not found: " + id, exception);
            }
            throw new BadGatewayException("Cannot read user id: " + id, exception);
        }
    }
}
