package es.upm.miw.apaw.domain.model.courthearing;

import jakarta.validation.constraints.Pattern;

import java.time.LocalTime;

public record CourtUpdate(
        @Pattern(regexp = ".*\\S.*")
        String name,
        String address,
        String city,
        String phone,
        LocalTime openingTime,
        LocalTime closingTime,
        CourtType type) {
}