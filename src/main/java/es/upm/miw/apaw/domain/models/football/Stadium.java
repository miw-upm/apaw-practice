package es.upm.miw.apaw.domain.models.football;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stadium {
    private Long stadiumId;
    private String officialName;
    private Integer capacity;
    private Boolean roof;
}