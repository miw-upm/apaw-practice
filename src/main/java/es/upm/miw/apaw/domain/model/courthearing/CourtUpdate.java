package es.upm.miw.apaw.domain.model.courthearing;

import java.time.LocalTime;

public record CourtUpdate(
        String name,
        String address,
        String city,
        String phone,
        LocalTime openingTime,
        LocalTime closingTime,
        CourtType type) {
}