package es.upm.miw.apaw.domain.restclients;

import es.upm.miw.apaw.domain.models.UserDto;

import java.util.UUID;

public interface UserRestClient {

    UserDto readById(UUID id);

    UserDto readByMobile(String mobile);
}
