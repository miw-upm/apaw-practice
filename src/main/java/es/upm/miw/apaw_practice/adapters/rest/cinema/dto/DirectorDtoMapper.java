package es.upm.miw.apaw_practice.adapters.rest.cinema.dto;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import java.time.LocalDate;

public class DirectorDtoMapper {
    private DirectorDtoMapper() {}

    public static DirectorDto toDto(Director director) {
        return new DirectorDto(
                director.getDni(),
                director.getBirthdate().toString(),
                director.getStyle()
        );
    }

    public static Director toDomain(DirectorDto dto) {
        return new Director(
                dto.getDni(),
                LocalDate.parse(dto.getBirthdate()),
                dto.getStyle()
        );
    }
}