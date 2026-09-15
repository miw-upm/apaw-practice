package es.upm.miw.apaw.adapters.out.user.feign;

import es.upm.miw.apaw.domain.models.UserSnapshot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "apaw-user", path = ApawUserClient.USERS)
public interface ApawUserClient {
    String USERS = "/users";

    @GetMapping("/{id}")
    UserSnapshot read(@PathVariable("id") UUID id);
}
