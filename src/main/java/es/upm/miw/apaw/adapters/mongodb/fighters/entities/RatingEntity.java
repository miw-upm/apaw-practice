package es.upm.miw.apaw.adapters.mongodb.fighters.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.fighters.Rating;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RatingEntity {
    @Id
    @EqualsAndHashCode.Include
    private UUID idRating;
    private Integer score;
    private String comment;
    private LocalDateTime createdAt;
    private UUID userId;
    public RatingEntity(Rating rating) {
        BeanUtils.copyProperties(rating, this);
        this.idRating = UUID.randomUUID();
        this.userId = rating.getUser().getId();
    }

    public void fromRating(Rating rating){
        BeanUtils.copyProperties(rating, this);
        this.userId = rating.getUser().getId();
    }

    public Rating toRating(){
        Rating rating = new Rating();
        BeanUtils.copyProperties(this, rating);
        rating.setUser(UserDto.builder().id(userId).build());
        return rating;
    }

}
