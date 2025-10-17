package es.upm.miw.apaw.adapters.mongodb.football.entities;

import es.upm.miw.apaw.domain.models.football.FootballPlayer;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FootballPlayerEntity {

    @Id
    @EqualsAndHashCode.Include
    private Long playerId;
    private String nickname;
    private LocalDate birthDate;
    private Integer goalsScored;

    public FootballPlayerEntity(FootballPlayer player) {
        BeanUtils.copyProperties(player, this);
    }

    public FootballPlayer toFootballPlayer() {
        FootballPlayer player = new FootballPlayer();
        BeanUtils.copyProperties(this, player);
        return player;
    }
}
