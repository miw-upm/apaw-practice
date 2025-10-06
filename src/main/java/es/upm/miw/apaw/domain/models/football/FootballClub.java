package es.upm.miw.apaw.domain.models.football;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FootballClub {
    private Long clubId;
    private String name;
    private BigDecimal budget;
    private LocalDate founded;

    // Relationships
    private List<FootballPlayer> players;
    private Stadium stadium;
}