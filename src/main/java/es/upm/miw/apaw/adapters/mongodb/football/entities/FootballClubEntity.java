package es.upm.miw.apaw.adapters.mongodb.football.entities;

import es.upm.miw.apaw.domain.models.football.FootballClub;
import es.upm.miw.apaw.domain.models.football.FootballPlayer;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FootballClubEntity {

    private static final String[] IGNORE_PROPERTIES = {"stadium", "players"};

    @Id
    private Long clubId;
    @EqualsAndHashCode.Include
    private String name;
    private BigDecimal budget;
    private LocalDate founded;
    private UUID userId;

    @DBRef
    private StadiumEntity stadium;

    @DBRef
    private List<FootballPlayerEntity> players = new ArrayList<>();

    // --- Constructor de conversión ---
    public FootballClubEntity(FootballClub club) {
        BeanUtils.copyProperties(club, this, IGNORE_PROPERTIES);
        if (club.getStadium() != null) {
            this.stadium = new StadiumEntity(club.getStadium());
        }
        if (club.getPlayers() != null) {
            this.players = club.getPlayers().stream()
                    .map(FootballPlayerEntity::new)
                    .toList();
        }
    }

    public FootballClub toFootballClub() {
        FootballClub club = new FootballClub();
        BeanUtils.copyProperties(this, club, IGNORE_PROPERTIES);

        if (this.stadium != null) {
            club.setStadium(this.stadium.toStadium());
        }
        if (this.players != null) {
            List<FootballPlayer> playerList = this.players.stream()
                    .map(FootballPlayerEntity::toFootballPlayer)
                    .toList();
            club.setPlayers(playerList);
        }
        return club;
    }
}
