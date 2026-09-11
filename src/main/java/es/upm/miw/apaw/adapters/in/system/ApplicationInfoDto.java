package es.upm.miw.apaw.adapters.in.system;

import java.time.LocalDateTime;

public record ApplicationInfoDto(String version, LocalDateTime timestamp) {
}

