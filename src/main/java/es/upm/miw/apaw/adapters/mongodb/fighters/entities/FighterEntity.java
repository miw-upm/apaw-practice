package es.upm.miw.apaw.adapters.mongodb.fighters.entities;

import es.upm.miw.apaw.domain.models.fighters.Fighter;
import es.upm.miw.apaw.domain.models.fighters.MartialArt;
import es.upm.miw.apaw.domain.models.fighters.Rating;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FighterEntity {
    @Id
    @EqualsAndHashCode.Include
    private String nickname;
    private String name;
    private String lastName;
    private Double weight;
    private Double height;
    private Integer wins;
    private Integer losses;
    private String country;
    private CoachEntity coach;
    private List<MartialArtEntity> martialArtsEntities = new ArrayList<>();
    private List<RatingEntity> ratingsEntities = new ArrayList<>();

    public FighterEntity(Fighter fighter) {
        BeanUtils.copyProperties(fighter, this,"coach","martialArts", "ratings");
        this.coach = new CoachEntity(fighter.getCoach());
        this.martialArtsEntities = fighter.getMartialArts().stream()
                .map(MartialArtEntity::new)
                .toList();
        this.ratingsEntities = fighter.getRatings().stream()
                .map(RatingEntity::new)
                .toList();
    }

    public Fighter toFighter() {
        Fighter fighter = new Fighter();
        BeanUtils.copyProperties(this, fighter,"coach","martialArts", "ratings");
        fighter.setCoach(this.coach.toCoach());
        List<MartialArt> martialArts = this.martialArtsEntities.stream()
                .map(MartialArtEntity::toMartialArt)
                .toList();
        fighter.setMartialArts(martialArts);
        List<Rating> ratings = this.ratingsEntities.stream()
                .map(RatingEntity::toRating)
                .toList();
        fighter.setRatings(ratings);
        return fighter;
    }
}
