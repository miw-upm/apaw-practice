package es.upm.miw.apaw.domain.models.videogame;

import es.upm.miw.apaw.domain.models.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeList {
    @NotNull
    @NotBlank
    private UUID listID;
    @NotNull
    private Boolean isPublic;
    private Integer likesCount;
    @NotNull
    private UserDto user;
    @NotEmpty
    private List<Videogame> gamesLiked;

}
