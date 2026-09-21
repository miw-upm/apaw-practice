package es.upm.miw.apaw.adapters.out.user.feign;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@FeignClient(name = "apaw-user", path = ApawUserClient.USERS)
public interface ApawUserClient {
    String USERS = "/users";

    @GetMapping("/{id}")
    UserSnapshot read(@PathVariable("id") UUID id);

    @GetMapping("/by-ids")
    List<UserSnapshot> findByIds(@RequestParam("ids") Set<UUID> ids);
}
