package es.upm.miw.apaw.domain.models.football;

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
}