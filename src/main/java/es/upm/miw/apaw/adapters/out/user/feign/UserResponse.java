package es.upm.miw.apaw.adapters.out.user.feign;

import java.util.UUID;

public record UserResponse(UUID id, String mobile, String firstName) {
}
