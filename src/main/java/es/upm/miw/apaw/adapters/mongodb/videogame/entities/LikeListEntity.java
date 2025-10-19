package es.upm.miw.apaw.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.videogame.Company;
import es.upm.miw.apaw.domain.models.videogame.LikeList;
import es.upm.miw.apaw.domain.models.videogame.Videogame;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;
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


    public LikeList toLikeList() {
        LikeList likeList = new LikeList();

        // Copiar propiedades simples
        BeanUtils.copyProperties(this, likeList, "gamesLikedEntity", "userId");

        // Asignar el usuario (como UserDto con solo el ID)
        if (this.userId != null) {
            UserDto userDto = new UserDto();
            userDto.setId(this.userId);
            likeList.setUser(userDto);
        }

        // Convertir los videojuegos asociados
        List<Videogame> videogames = Optional.ofNullable(this.gamesLikedEntity)
                .orElse(List.of())
                .stream()
                .map(VideogameEntity::toVideogame)
                .toList();

        likeList.setGamesLiked(videogames);

        return likeList;
    }
}
