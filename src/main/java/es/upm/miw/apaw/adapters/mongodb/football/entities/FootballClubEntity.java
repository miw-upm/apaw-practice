package es.upm.miw.apaw.adapters.mongodb.football.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "football_club")
public class FootballClubEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private Long clubId;

    private String name;
    private BigDecimal budget;
    private LocalDate founded;

    private UUID userId;

    @DBRef
    private List<FootballPlayerEntity> players;

    @DBRef
    private StadiumEntity stadium;
}

