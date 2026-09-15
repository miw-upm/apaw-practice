package es.upm.miw.apaw.adapters.out.user.feign;

import es.upm.miw.apaw.domain.exceptions.BadGatewayException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserSnapshot;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;
import java.util.function.Supplier;

@Repository
@RequiredArgsConstructor
public class UserFinderAdapter implements UserFinder {
    private final ApawUserClient apawUserClient;

    @Override
    public UserSnapshot read(UUID id) {
        return this.call(() -> this.apawUserClient.read(id), " on read user by id " + id );
    }

    @Override
    public Optional<UserSnapshot> findByMobile(String mobile) {
        return this.call(() -> this.apawUserClient.findByMobile(mobile).stream().findFirst(),
                " on find user by mobile " + mobile);
    }

    private <T> T call(Supplier<T> supplier, String operation) {
        try {
            return supplier.get();
        } catch (FeignException exception) {
            if (exception.status() == HttpStatus.NOT_FOUND.value()) {
                throw new NotFoundException("Not found" + operation, exception);
            }
            throw new BadGatewayException("Cannot complete" + operation, exception);
        }
    }
}
