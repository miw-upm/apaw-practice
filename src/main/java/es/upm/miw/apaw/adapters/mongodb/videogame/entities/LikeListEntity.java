package es.upm.miw.apaw.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw.domain.models.videogame.LikeList;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class LikeListEntity {
    @EqualsAndHashCode.Include
    @Id
    private UUID id;
    private Boolean shared;
    private Integer likesCount;
    private UUID userId;
    @DBRef
    private List<VideogameEntity> gamesLikedEntity;



}
