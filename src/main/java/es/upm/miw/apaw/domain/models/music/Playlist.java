package es.upm.miw.apaw.domain.models.music;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    @NotNull
    @NotBlank
    private String code;
    @NotNull
    @NotBlank
    private String label;
    @Builder.Default
    private Boolean opened = Boolean.TRUE;
    @Builder.Default
    private List<Song> songs = new ArrayList<>();
}