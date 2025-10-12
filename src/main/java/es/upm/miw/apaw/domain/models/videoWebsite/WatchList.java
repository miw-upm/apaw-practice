package es.upm.miw.apaw.domain.models.videoWebsite;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchList {
    @NotBlank
    private String listName;
    private String description;
    @NotNull
    private List<Video> savedVideos;
}
