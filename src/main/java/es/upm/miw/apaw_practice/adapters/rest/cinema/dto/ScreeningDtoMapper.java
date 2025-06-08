package es.upm.miw.apaw_practice.adapters.rest.cinema.dto;

import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import java.time.LocalDateTime;

public class ScreeningDtoMapper {
    private ScreeningDtoMapper() {}

    public static ScreeningDto toDto(Screening screening) {
        return new ScreeningDto(
                screening.getScreeningTime().toString(),
                screening.getThreeDFormat(),
                screening.getAvailableSeats()
        );
    }

    public static Screening toDomain(ScreeningDto dto) {
        return new Screening(
                LocalDateTime.parse(dto.getScreeningTime()),
                dto.getThreeDFormat(),
                dto.getAvailableSeats()
        );
    }
}