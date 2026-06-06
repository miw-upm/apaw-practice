package es.upm.miw.apaw.domain.models.football;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stadium {

    private UUID stadiumId;
    private String officialName;
    private Integer capacity;
    private Boolean roof;

    public void validate() {
        if (officialName == null || officialName.isBlank()) {
            throw new BadRequestException("Official name cannot be null or blank");
        }
        if (capacity == null || capacity <= 0) {
            throw new BadRequestException("Capacity must be greater than 0");
        }
    }
}